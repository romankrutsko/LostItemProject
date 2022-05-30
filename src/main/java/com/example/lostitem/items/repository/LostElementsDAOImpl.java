package com.example.lostitem.items.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.lostitem.db.DatabaseConnection;
import com.example.lostitem.logIn.service.Role;
import org.springframework.stereotype.Repository;

import com.example.lostitem.items.service.LostElement;
import com.example.lostitem.logIn.service.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Repository
public class LostElementsDAOImpl implements LostElementsDAO {

	private final Connection connection = DatabaseConnection.getDBConnection().getConnection();

	private final List<LostElement> elements;

	private int nextNumber = 1;
			
	public LostElementsDAOImpl(List<LostElement> elements) throws SQLException {
		this.elements = elements;
	}
	
	@Override
	public List<LostElement> itemGetAll()
	{
		System.out.println("elements.size() " + elements.size());
		return elements;
	}

	
	
	@Override
	public ArrayList<LostElement> itemSearchByKeywords(List<String> keywords)
	{
		System.out.println("DAOKeywords = " + keywords.get(0));

		
		ArrayList<LostElement> selectedElements = new ArrayList<LostElement>();
		
		int lookedFor = keywords.size();
		
		for (LostElement lostElement : elements)
		{
			int currentLookedFor = 0;
			
			for (String elementKeyword : lostElement.getKeywords())
			{
				for (String lokingForKeyword : keywords)
				{
					
				    if(elementKeyword.equals(lokingForKeyword)) {currentLookedFor++;}
				}
			}
			
		    if(currentLookedFor == lookedFor) {selectedElements.add(lostElement);}
		    
		}
		
		selectedElements.size();
		
		return selectedElements;
	}
	
	
	@Override
	public List<LostElement> itemSearchByOwner(String founder)
	{
		List<LostElement> selectedElements = new ArrayList<LostElement>();
		
		
		for (LostElement lostElement : elements)
		{
		    if(lostElement.getFounderUsername() == founder) {selectedElements.add(lostElement);}
		}
		return selectedElements;
	}

		
	@Override
	public List<LostElement> itemSearchByKeywordsAndOwner(List<String> keywords, String founder)
	{
		List<LostElement> selectedElements = new ArrayList<LostElement>();
			
		int lookedFor = keywords.size();
			
		for (LostElement lostElement : elements)
		{
			int currentLookedFor = 0;
				
			for (String elementKeyword : lostElement.getKeywords())
			{
				for (String lokingForKeyword : keywords)
				{
						
					if(elementKeyword == lokingForKeyword) {currentLookedFor++;}
				}
			}
				
			if((currentLookedFor == lookedFor)&&(lostElement.getFounderUsername() == founder)) {selectedElements.add(lostElement);}
			    
			selectedElements.add(lostElement);
		}
			
		return selectedElements;
	}
	
		
	@Override
	public void addNewItem(int idUser, String nameItem, String keywords) {
		try (PreparedStatement statement = connection.prepareStatement(
				"insert into lostitem (user_id, name_item, keywords) values (?, ?, ?)"
		)) {
			statement.setInt(1, idUser);
			statement.setString(2, nameItem);
			statement.setString(3, keywords);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteItem(int itemID, HttpSession session, Model model) {
		try (PreparedStatement statement = connection.prepareStatement(
				"delete from lostitem where id=? and user_id=?")) {
			statement.setInt(1, itemID);
			statement.setInt(2, (int) session.getAttribute("userid"));
			statement.executeUpdate();
		} catch (SQLException e) {
			model.addAttribute("noSuchItemToDelete", true);
			throw new RuntimeException(e);
		}
	}

}
