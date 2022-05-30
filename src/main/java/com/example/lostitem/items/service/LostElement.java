package com.example.lostitem.items.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LostElement
{
	private int number;
	private String name;
	private String founderUsername;
	private List<String> keywords;
	
	public LostElement()
	{

	}
	
	public LostElement(int number, String name, String founderUsername, List<String> keywords)
	{
		super();
		this.number = number;
		this.name = name;
		this.founderUsername = founderUsername;
		this.keywords = keywords;
	}

	public LostElement(String name, String founderUsername, List<String> keywords)
	{
		this.name = name;
		this.founderUsername = founderUsername;
		this.keywords = keywords;
	}
	
	public int getNumber() {return number;}
	
	public String getName() {return name;}
	
	public String getFounderUsername() {return founderUsername;}
	
	public List<String> getKeywords() {return keywords;}
	
	
}
