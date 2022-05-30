package com.example.lostitem.logIn.service;


import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public interface LogIn
{
public User tryLogIn(String name, String password, HttpSession session) throws SQLException;

public User getCurrentUser();

public String getCurrentUserUsername();

}
