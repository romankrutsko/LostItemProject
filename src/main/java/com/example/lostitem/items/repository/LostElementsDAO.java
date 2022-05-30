package com.example.lostitem.items.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.lostitem.items.service.LostElement;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface LostElementsDAO
{
	public List<LostElement> itemGetAll();
	
	public ArrayList<LostElement> itemSearchByKeywords(List<String> keywords);
	
	public List<LostElement> itemSearchByOwner(String founder);
	
	public List<LostElement> itemSearchByKeywordsAndOwner(List<String> keywords, String founder);
	
	void addNewItem(int idUser, String nameItem, String keywords);
	
	void deleteItem(int itemID, HttpSession session, Model model);
}
