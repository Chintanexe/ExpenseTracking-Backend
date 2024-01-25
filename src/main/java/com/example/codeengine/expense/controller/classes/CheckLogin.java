package com.example.codeengine.expense.controller.classes;

import lombok.Data;

@Data
public class CheckLogin {

    private final String status;
    private final String username;

    public CheckLogin(String _status, String _username) {
        this.status = _status;
        this.username = _username;
    }


}
