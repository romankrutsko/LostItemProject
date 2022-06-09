package com.example.lostitem.rest.repo;

import com.example.lostitem.db.DatabaseConnection;
import com.example.lostitem.items.model.Page;
import com.example.lostitem.rest.model.LostItem;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final static String SELECT_ALL_QUERY = "SELECT * FROM lostitem ORDER BY lostitem.id LIMIT ? OFFSET ?";
    private final static String SELECT_BY_ID_QUERY = "SELECT * FROM lostitem WHERE id = ?";
    private final static String SAVE_ELEMENT_QUERY = "insert into lostitem(user_id, name_item, keywords) VALUES (?, ?, ?);";
    private final static String UPDATE_ELEMENT_QUERY = "update lostitem set %s where id = ?";
    private final static String DELETE_ELEMENT_QUERY = "DELETE FROM lostitem WHERE id =?";

    private final Connection connection = DatabaseConnection.getDBConnection().getConnection();

    public ItemRepositoryImpl() throws SQLException {
    }

    @Override
    public Page<LostItem> getAllElementsPageable(Page<LostItem> request) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            int offset = request.getPageId() * request.getPageSize();
            statement.setInt(1, request.getPageSize());
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();

            List<LostItem> lostElements = new ArrayList<>();
            while (resultSet.next()) {
                lostElements.add(new LostItem(resultSet.getInt("id"), resultSet.getString("name_item"), resultSet.getInt("user_id"), new ArrayList<>(Collections.singleton(resultSet.getString("keywords")))));
            }
            request.setData(lostElements);
            return request;
        }
    }

    @Override
    public Optional<LostItem> getElementById(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(new LostItem(resultSet.getInt("id"), resultSet.getString("name_item"), resultSet.getInt("user_id"), new ArrayList<>(Collections.singleton(resultSet.getString("keywords")))));
        }
    }

    @Override
    public Integer saveElement(LostItem item) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_ELEMENT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, item.getUserId());
            statement.setString(2, item.getItemName());
            statement.setString(3, String.join(",", item.getKeywords()));
            final int affected = statement.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Item wasn`t saved");
            }
            final ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            return generatedKeys.getInt(1);
        }
    }

    @Override
    public Optional<LostItem> updateElement(LostItem item) throws SQLException {
        StringBuilder builder = new StringBuilder();
        if (item.getUserId() != null) {
            builder.append("user_id = ").append(item.getUserId()).append(",");
        }
        if (item.getItemName() != null) {
            builder.append("name_item = '").append(item.getItemName()).append("',");
        }
        if (item.getKeywords() != null) {
            builder.append("keywords = '").append(String.join(",", item.getKeywords())).append("',");
        }
        builder.deleteCharAt(builder.lastIndexOf(","));
        final String result = builder.toString();
        if (result.isEmpty()) {
            return getElementById(item.getId());
        }
        final String request = String.format(DELETE_ELEMENT_QUERY, result);
        try (PreparedStatement statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, item.getId());
            statement.executeUpdate();
            return getElementById(item.getId());
        }
    }

    @Override
    public void deleteElementById(Integer id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ELEMENT_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
