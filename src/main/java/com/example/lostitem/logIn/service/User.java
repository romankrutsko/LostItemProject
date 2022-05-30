package com.example.lostitem.logIn.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class User
{
	private String name;
	private String password;
	private List<String> hobbies;
	private Role role;
	
	public User(String name, String password)
	{
		this.name = name;
		this.password = password;
		this.role = Role.USER;
		hobbies = new ArrayList<>();
	}

	public User(String name, String password, Role role)
	{
		this.name = name;
		this.password = password;
		this.role = role;
		hobbies = new ArrayList<>();
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getHobbies() {
		return hobbies;
	}

	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
