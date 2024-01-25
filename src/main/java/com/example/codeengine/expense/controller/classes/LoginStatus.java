package com.example.codeengine.expense.controller.classes;

import lombok.Data;

@Data
public class LoginStatus {
    private String token;
    private String status;

    private String username;

    private String name;

    public LoginStatus(String _token, String _status, String _username, String _name) {
        this.token = _token;
        this.status = _status;
        this.username = _username;
        this.name = _name;
    }
}
