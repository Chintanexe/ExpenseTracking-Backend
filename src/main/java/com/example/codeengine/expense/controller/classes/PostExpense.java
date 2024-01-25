package com.example.codeengine.expense.controller.classes;

import com.example.codeengine.expense.model.Expense;
import lombok.Data;

@Data
public class PostExpense {

    private double price;
    private String name;
    private String description;
    private Expense.Category category;
    private String[] users;

}
