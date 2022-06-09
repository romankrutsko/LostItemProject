package com.example.lostitem.rest.repo;

import com.example.lostitem.items.model.Page;
import com.example.lostitem.rest.model.LostItem;

import java.sql.SQLException;
import java.util.Optional;

public interface ItemRepository {
    Page<LostItem> getAllElementsPageable(Page<LostItem> request) throws SQLException;
    Optional<LostItem> getElementById(Integer id) throws SQLException;
    Integer saveElement(LostItem item) throws SQLException;
    Optional<LostItem> updateElement(LostItem item) throws SQLException;
    void deleteElementById(Integer id) throws SQLException;
 }
