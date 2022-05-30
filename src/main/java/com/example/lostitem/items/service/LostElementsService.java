package com.example.lostitem.items.service;

import com.example.lostitem.logIn.service.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

public interface LostElementsService
{


	List<LostElement> getAllElements(User user) throws SQLException;

	List<LostElement> filterByKeywords(User user, String keyword) throws SQLException;

	List getSelectedElements() throws SQLException;
	
	List<String> getCurrentKeywords();
	void setCurrentKeywords(String stringKeywords);
	void setAtLeastOneCurrentKeywords(boolean _atLeastOneCurrentKeywords);
	
	Boolean getMineOnly();
	void changeMineOnly();

	
	public void addNewItem(int idUser, String addItemName, String stringAddItemKeywords);
	
	public void deleteItem(int deleteIndex, HttpSession session, Model model);
	
}
