package com.example.lostitem.logIn.repository;

import java.sql.SQLException;

import com.example.lostitem.items.service.LostElementsService;
import com.example.lostitem.logIn.service.Role;
import com.example.lostitem.logIn.service.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface UserDAO
{
	public User getUser(String name, String password, HttpSession session) throws SQLException;

	public User getUserById(int id) throws SQLException;

	public void setModel(Model model, User user, LostElementsService lostElementsService, String keywords) throws SQLException;
}
