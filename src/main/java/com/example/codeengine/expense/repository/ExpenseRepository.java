package com.example.codeengine.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.codeengine.expense.model.Expense;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findALlByUsersContaining(String substring);
}
