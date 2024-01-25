package com.example.codeengine.expense.controller;

import com.example.codeengine.expense.controller.classes.*;
import com.example.codeengine.expense.model.User;
import com.example.codeengine.expense.middleware.JwtUtil;
import com.example.codeengine.expense.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/profile")
    @ResponseBody
    public CheckLogin checkLogin(@RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            String username = jwtUtil.extractUsername(token);
            return new CheckLogin("SUCCESS", username);
        } catch (Exception e) {
            return new CheckLogin(e.getMessage(), "");
        }
    }

    @GetMapping("/all")
    List<UsersShow> showCaseUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UsersShow(user.getName()))
                .collect(Collectors.toList());
    }

    @PostMapping("/login")
    @ResponseBody
    public LoginStatus loginUser(@RequestBody UserLoginRequest userLoginRequest) throws Exception {
        try {
            User user = userRepository.findByUsername(userLoginRequest.getUsername());

            if (user == null) throw new Exception("No User Found!");

            if (!userLoginRequest.getPassword().equals(user.getPassword())) {
                throw new Exception("Authentication Failed!");
            }

            String token = jwtUtil.generateToken(user.getUsername());

            user.setToken(token);
            userRepository.save(user);

            return new LoginStatus(token, "SUCCESS", user.getUsername(), user.getName());
        } catch (Exception e) {
            return new LoginStatus("", e.getMessage(), "", "");
        }
    }

    @PostMapping("/signup")
    @ResponseBody
    public SignupStatus signupUser(@RequestBody UserSignupRequest userSignupRequest) throws Exception {
        try {
            User existingUser = userRepository.findByUsername(userSignupRequest.getUsername());

            if (existingUser != null) throw new Exception("User Already Exists");

            User user = new User(
                    userSignupRequest.getUsername(),
                    userSignupRequest.getPassword(),
                    userSignupRequest.getName()
            );

            userRepository.save(user);

            return new SignupStatus(
                    userSignupRequest.getUsername(),
                    userSignupRequest.getPassword(),
                    "SUCCESS"
            );

        } catch (Exception e){
            return new SignupStatus("", "", e.getMessage());
        }
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<?> logout(@RequestHeader(name = "Authorization") String token) throws Exception {
        try {
            String username = jwtUtil.extractUsername(token);
            User existingUser = userRepository.findByUsername(username);
            existingUser.setToken("");
            userRepository.save(existingUser);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
