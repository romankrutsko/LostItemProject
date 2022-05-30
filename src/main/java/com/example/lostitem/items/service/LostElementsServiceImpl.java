package com.example.lostitem.items.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.example.lostitem.db.DatabaseConnection;
import com.example.lostitem.logIn.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lostitem.items.repository.LostElementsDAO;
import com.example.lostitem.logIn.service.LogIn;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class LostElementsServiceImpl implements LostElementsService {

	boolean mineOnly = false;
	List<String> currentKeywords = new ArrayList<String>();
	boolean atLeastOneCurrentKeywords = false;
	
	//@Autowired
	List<LostElement> selectedElements;
	@Autowired
	LostElementsDAO lostElementsDAO;
	
	@Autowired
	LogIn logIn;

	private final Connection connection = DatabaseConnection.getDBConnection().getConnection();

	public LostElementsServiceImpl() throws SQLException {
	}

	@Override
	public List<LostElement> getAllElements(User user) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(
				"select l.id, u.name, l.name_item, l.keywords from lostitem l left join user u on l.user_id = u.id where u.name=?"
		);
		statement.setString(1, user.getName());
		ResultSet resultSet = statement.executeQuery();

		List<LostElement> lostElements = new ArrayList<>();
		while (resultSet.next()) {
			lostElements.add(new LostElement(resultSet.getInt("id"), resultSet.getString("name_item"), resultSet.getString("name"), new ArrayList<>(Collections.singleton(resultSet.getString("keywords")))));
		}
		return lostElements;
	}

	@Override
	public List<LostElement> filterByKeywords(User user, String keyword) throws SQLException {
		List<LostElement> filtered = new ArrayList<>();
		List<LostElement> allElements = getAllElements(user);
		for (LostElement lostElement:allElements) {
			if (lostElement.getKeywords().contains(keyword)) {
				filtered.add(lostElement);
			}
		}
		return filtered;
	}


	@Override
	public List getSelectedElements() throws SQLException {
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
	
		// getselectedelemnts from dao
		

		
		
		// if selectedelements = null {}
		
		
		//System.out.println("currentKeywords.size()" + currentKeywords.size() + "currentKeywords.get(0)" + currentKeywords.get(0));
		
		///ArrayList<LostElement> NEWselectedElements = new ArrayList<LostElement>();

		Statement statement = connection.createStatement();

		LostElement lostElement = new LostElement();

		if((mineOnly == false)&&(atLeastOneCurrentKeywords == false)) {
			System.out.println("case 1");

//			ResultSet resultSet = statement.executeQuery("select * from orders where id=" + id + ";");
			selectedElements = lostElementsDAO.itemGetAll();
		} else if((mineOnly == false)&&(atLeastOneCurrentKeywords == true)) {
			System.out.println("case 2");
			selectedElements = lostElementsDAO.itemSearchByKeywords(currentKeywords);
		} else if((mineOnly == true)&&(atLeastOneCurrentKeywords == false)) {
			System.out.println("case 3");
			lostElementsDAO.itemSearchByOwner(logIn.getCurrentUserUsername());
		} else if((mineOnly == true)&&(atLeastOneCurrentKeywords == true)) {
			System.out.println("case 4");
			lostElementsDAO.itemSearchByKeywordsAndOwner(currentKeywords, logIn.getCurrentUserUsername());
		}
		
		
		//if((mineOnly == false)&&(currentKeywords.size() == 0)) {System.out.println("1"); lostElementsDAO.itemGetAll();}
		//else if((mineOnly == false)&&(currentKeywords.size() != 0)) {System.out.println("2"); lostElementsDAO.itemSearchByKeywords(currentKeywords);}
		//else if((mineOnly == true)&&(currentKeywords.size() == 0)) {System.out.println("3"); lostElementsDAO.itemSearchByOwner(logIn.getCurrentUserUsername());}
		//else if((mineOnly == true)&&(currentKeywords.size() != 0)) {System.out.println("4"); lostElementsDAO.itemSearchByKeywordsAndOwner(currentKeywords, logIn.getCurrentUserUsername());}
		
		
		//if((mineOnly == false)&&(selectedElements.size() == 0)) {;}
		//else if((mineOnly == false)&&(selectedElements.size() != 0)) {;}
		//else if((mineOnly == true)&&(selectedElements.size() == 0)) {;}
		//else if((mineOnly == true)&&(selectedElements.size() != 0)) {;}
		

		
		//selectedElements = NEWselectedElements;
		
		System.out.println("selectedElements.size() " + selectedElements.size());
		
		return selectedElements;
	}

	
	@Override
	public List<String> getCurrentKeywords() {
		// TODO Auto-generated method stub
		return currentKeywords;
	}

	@Override
	public void setCurrentKeywords(String stringKeywords)
	{
		currentKeywords = Arrays.asList(stringKeywords.split(" "));
		//currentKeywords = Arrays.asList(stringKeywords.split("\\s*,\\s*"));
	}
	
	@Override
	public void setAtLeastOneCurrentKeywords(boolean _atLeastOneCurrentKeywords) {atLeastOneCurrentKeywords = _atLeastOneCurrentKeywords;}
	

	@Override
	public Boolean getMineOnly() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void changeMineOnly() 
	{
		if(mineOnly == false) {mineOnly = true;}
		else if(mineOnly == true) {mineOnly = false;}
	}


	@Override
	public void addNewItem(int idUser, String addItemName, String stringAddItemKeywords)
	{
		lostElementsDAO.addNewItem(idUser, addItemName, stringAddItemKeywords);
	}
	
	@Override
	public void deleteItem(int deleteIndex, HttpSession session, Model model)
	{
		lostElementsDAO.deleteItem(deleteIndex, session, model);
	}
	

}
