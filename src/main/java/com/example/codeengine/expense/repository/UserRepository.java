package com.example.codeengine.expense.repository;

import com.example.codeengine.expense.model.Expense;
import com.example.codeengine.expense.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
