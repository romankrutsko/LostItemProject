package com.example.lostitem.logIn.service;

import org.springframework.stereotype.Service;

import com.example.lostitem.logIn.repository.UserDAO;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;


@Service
public class LogInImpl implements LogIn
{
	public static final Boolean isLoggedIn = null;
	public User currentUser;
	public UserDAO userDAO;
	
	
	public LogInImpl(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}
	 
	
	@Override
	public User tryLogIn(String name, String password, HttpSession session) throws SQLException {
		if(userDAO.getUser(name, password, session) != null)
		{
			currentUser = userDAO.getUser(name, password, session);
		} else {
			return null;
		}

		return currentUser;
	}

	@Override
	public User getCurrentUser() {
		// TODO Auto-generated method stub
		return currentUser;
	}

	@Override
	public String getCurrentUserUsername()
	{
		return(currentUser.getName());
	};
	
	
}
