package com.example.codeengine.expense.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name="USERS")
public class User {

	@Id
	private String username;

	private String name;

	private String password;

	private String token;

	public String getName() {
		return name;
	}

	public User(String _username, String _password, String _name) {
		// this.id = new Date().getTime();
		this.username = _username;
		this.name = _name;
		this.password = _password;
	}
}

