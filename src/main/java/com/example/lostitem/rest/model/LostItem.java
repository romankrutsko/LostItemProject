package com.example.lostitem.rest.model;

import java.util.List;

public class LostItem {
    private Integer id;
    private String itemName;
    private Integer userId;
    private List<String> keywords;

    public LostItem() {
    }

    public LostItem(Integer id, String itemName, Integer userId, List<String> keywords) {
        this.id = id;
        this.itemName = itemName;
        this.userId = userId;
        this.keywords = keywords;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
