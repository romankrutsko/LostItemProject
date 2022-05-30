package com.example.lostitem.logIn.controller;

import com.example.lostitem.items.repository.LostElementsDAOImpl;
import com.example.lostitem.items.service.LostElementsServiceImpl;
import com.example.lostitem.logIn.repository.UserDAOStub;
import com.example.lostitem.logIn.service.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.lostitem.items.service.LostElementsService;
import com.example.lostitem.logIn.service.LogIn;
import com.example.lostitem.logIn.service.User;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Objects;

@Controller
public class RootController
{
	@Autowired
	LogIn logIn;
	
	@Autowired
	LostElementsService lostElementsService;
	
	@GetMapping("/")
	public String root(
		Model model,
		HttpSession session) throws SQLException {
		if (session.getAttribute("userid") != null) {
			UserDAOStub userDAOStub = new UserDAOStub();
			User user = userDAOStub.getUserById((int) session.getAttribute("userid"));
			userDAOStub.setModel(model, user, lostElementsService, "nothing");
			return("view");
		} else {
			return("index");
		}
	}
	
	@GetMapping("/view")
	public String view(
			@RequestParam(name = "usernameToDB", required = true, defaultValue = "0") String usernameToDB,
			@RequestParam(name = "passwordToDB", required = true, defaultValue = "0") String passwordToDB,
			//@RequestParam(name = "addItemName", required = false, defaultValue = "null") String addItemName,
			//@RequestParam(name = "stringAddNewItemKeywords", required = false, defaultValue = "nothing") String stringAddNewItemKeywords,
			@RequestParam(name = "stringSearchByKeywords", required = false, defaultValue = "nothing") String stringSearchByKeywords,
			@RequestParam(name = "mineOnly", required = false, defaultValue = "0") Boolean changeMineOnly,
			//@RequestParam(name = "deleteindex", required = false, defaultValue = "-1") int deleteIndex,
			Model model,
			HttpSession session) throws SQLException {
		User user;
		UserDAOStub userDAOStub = new UserDAOStub();
		if (session.getAttribute("userid") != null) {

			user = userDAOStub.getUserById((int) session.getAttribute("userid"));
		} else {
			if (logIn.tryLogIn(usernameToDB, passwordToDB, session) != null) {
				user = logIn.tryLogIn(usernameToDB, passwordToDB, session);
			} else {
				model.addAttribute("noSuchUser", true);
				return ("/index");
			}
		}

		//System.out.println("stringAddItemKeywords = " + stringAddNewItemKeywords);
		System.out.println("stringSearchKeywords = " + stringSearchByKeywords);

		userDAOStub.setModel(model, user, lostElementsService, stringSearchByKeywords);

//		if(stringSearchByKeywords.equals("nothing"))
//		{
//			model.addAttribute("lostElements", lostElementsService.getAllElements(user));
//			System.out.println("right");
//		}
//		else
//		{
//			model.addAttribute("lostElements", lostElementsService.filterByKeywords(user, stringSearchByKeywords));
//			System.out.println("wrong");
//		}
//
//
//		model.addAttribute("keywords", lostElementsService.getCurrentKeywords());
//		model.addAttribute("username", user.getName());
//		model.addAttribute("role", user.getRole());
		//model.addAttribute("keywords", lostElementsService.getCurrentKeywords());
		//model.addAttribute("mineOnly", lostElementsService.getMineOnly());
		
		return("view");
	}
	
	
	@PostMapping("/add")
	public String add(
		@RequestParam(name = "addNewItemName", required = true, defaultValue = "null") String addNewItemName,
		@RequestParam(name = "stringAddNewItemKeywords", required = true, defaultValue = "nothing") String stringAddNewItemKeywords,
		HttpSession session)
	{
		System.out.println("addNewItemName = " + addNewItemName);
		System.out.println("stringAddNewItemKeywords = " + stringAddNewItemKeywords);
		
		lostElementsService.addNewItem((int) session.getAttribute("userid"), addNewItemName, stringAddNewItemKeywords);
		
		return("redirect:/view");
	}
	
	@PostMapping("/delete")
	public String delete(
		@RequestParam(name = "deleteItemID", required = true, defaultValue = "null") String deleteItemID,
		Model model,
		HttpSession session)
	{
		System.out.println("deleteItemID = " + deleteItemID);
		
		lostElementsService.deleteItem(Integer.parseInt(deleteItemID), session, model);
		
		return("redirect:/view");
	}

	@GetMapping("/logoutGreeting")
	public String logout(
			HttpSession session) {
		session.removeAttribute("username");
		session.removeAttribute("userid");
		return("logoutGreeting");
	}
	
}


