package com.example.codeengine.expense.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.example.codeengine.expense.controller.classes.PostExpense;
import com.example.codeengine.expense.middleware.JwtUtil;
import com.example.codeengine.expense.model.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.codeengine.expense.model.Expense;
import com.example.codeengine.expense.repository.ExpenseRepository;

@RestController
@CrossOrigin
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private JwtUtil jwtUtil;
	
	@GetMapping()
	ResponseEntity<?> getExpenses(@RequestHeader(name = "Authorization") String token) {
		try {
			String username = jwtUtil.extractUsername(token);
			List<Expense> expenses = expenseRepository.findAll();
			List<Expense> result = new ArrayList<>();

			for (Expense expense: expenses) {
				List<String> users = Arrays.asList(expense.getUsers().split(","));

				for (int i = 0; i < users.size(); i++) {
					if (!users.get(i).equals(username)) continue;
					result.add(expense);
					break;
				}
			}

			return ResponseEntity.ok(result);

		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("{id}")
	ResponseEntity<?>  deleteExpense(@PathVariable Long id, @RequestHeader(name = "Authorization") String token){
		try {
			String username = jwtUtil.extractUsername(token);
			expenseRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping()
	ResponseEntity<?> createExpense(@Valid @RequestBody PostExpense postExpense, @RequestHeader(name = "Authorization") String token) throws Exception{
		try {
			String username = jwtUtil.extractUsername(token);
			String saveUserName = "";

			System.out.println(postExpense.getUsers().length);

			if (postExpense.getUsers().length > 0)
				saveUserName = username + "," + String.join(",", postExpense.getUsers());
			else saveUserName = username;

			Expense expense = new Expense(
					username,
					postExpense.getName(),
					postExpense.getDescription(),
					postExpense.getCategory(),
					saveUserName,
					postExpense.getPrice(),
					postExpense.getUsers().length
			);

			expenseRepository.save(expense);
			return ResponseEntity.ok(expense);

		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
