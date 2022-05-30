package com.example.lostitem.logIn.repository;

import com.example.lostitem.db.DatabaseConnection;
import com.example.lostitem.items.service.LostElementsService;
import com.example.lostitem.logIn.service.Role;
import org.springframework.stereotype.Repository;

import com.example.lostitem.logIn.service.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDAOStub implements UserDAO {

	private final Connection connection = DatabaseConnection.getDBConnection().getConnection();

	public UserDAOStub() throws SQLException {
	}

	@Override
	public User getUser(String name, String password, HttpSession session) throws SQLException {
		User user;
		PreparedStatement statement = connection.prepareStatement(
				"select id, name, password, role from user where name=? and password=?"
		);
		statement.setString(1, name);
		statement.setString(2, password);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			if (resultSet.getString("role").equals(Role.ADMIN.toString())) {
				user = new User(resultSet.getString("name"), resultSet.getString("password"), Role.ADMIN);
			} else {
				user = new User(resultSet.getString("name"), resultSet.getString("password"), Role.USER);
			}
			session.setAttribute("username", resultSet.getString("name"));
			session.setAttribute("userid", resultSet.getInt("id"));
			return user;
		} else {
			return null;
		}
	}

	@Override
	public User getUserById(int id) throws SQLException {
		User user;
		PreparedStatement statement = connection.prepareStatement(
				"select id, name, password, role from user where id=?"
		);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			if (resultSet.getString("role").equals(Role.ADMIN.toString())) {
				user = new User(resultSet.getString("name"), resultSet.getString("password"), Role.ADMIN);
			} else {
				user = new User(resultSet.getString("name"), resultSet.getString("password"), Role.USER);
			}
			return user;
		} else {
			return null;
		}
	}

	@Override
	public void setModel(Model model, User user, LostElementsService lostElementsService, String stringSearchByKeywords) throws SQLException {
		if(stringSearchByKeywords.equals("nothing"))
		{
			model.addAttribute("lostElements", lostElementsService.getAllElements(user));
			System.out.println("right");
		}
		else
		{
			model.addAttribute("lostElements", lostElementsService.filterByKeywords(user, stringSearchByKeywords));
			System.out.println("wrong");
		}


		model.addAttribute("keywords", lostElementsService.getCurrentKeywords());
		model.addAttribute("username", user.getName());
		model.addAttribute("role", user.getRole());
	}
}
