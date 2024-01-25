package com.example.codeengine.expense.model;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="EXPENSES")
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date expenseDate;

	private double price;

	private String name;

	private String description;

	private Category category;

	private String username;

	private String users;

	private int splitCount;

	public String getUsers() {
		return users;
	}

	public Expense(String _username, String _name, String _description, Category _category, String _users, double _price, int splitUserCount) {
		this.username = _username;
		this.name = _name;
		this.description = _description;
		this.category = _category;
		this.users = _users;
		this.price = _price;
		this.expenseDate = new Date();
		this.splitCount = 1 + splitUserCount;
	}

	public double getPrice() {
		return price;
	}

	public enum Category {
		Health,
		Food,
		Recreation,
		Investments,
		Clothing,
		Others
	}
}
