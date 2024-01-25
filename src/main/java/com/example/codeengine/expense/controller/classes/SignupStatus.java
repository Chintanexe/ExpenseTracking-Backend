package com.example.codeengine.expense.controller.classes;

import lombok.Data;

@Data
public class SignupStatus {
    private String username;
    private String password;
    private String status;

    public SignupStatus(String _username, String _password, String _status) {
        this.username = _username;
        this.password = _password;
        this.status = _status;
    }
}
