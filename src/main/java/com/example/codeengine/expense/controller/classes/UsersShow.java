package com.example.codeengine.expense.controller.classes;

import lombok.Data;

@Data
public class UsersShow {
    // private Long id;
    private String name;

    public UsersShow(String name) {
        this.name = name;
    }
}
