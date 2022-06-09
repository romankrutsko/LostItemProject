package com.example.lostitem.rest.service.LostItemRestService;

import com.example.lostitem.items.model.Page;
import com.example.lostitem.rest.model.LostItem;
import com.example.lostitem.rest.repo.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LostItemRestService {
    private final ItemRepository itemRepository;

    public LostItemRestService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Page<LostItem> getAllElementsPageable(Page<LostItem> request, LostItem filterItem) throws ResponseStatusException {
        if (request.getPageSize() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page size couldn`t be 0 or bellow");
        }
        if (request.getPageId() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page couldn`t be bellow 0");
        }
        try {
            return itemRepository.getAllElementsPageable(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwables.getMessage());
        }
    }

    public Optional<LostItem> getElementById(Integer id) throws ResponseStatusException {
        if (id < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id can`t be negative");
        }
        try {
            return itemRepository.getElementById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwables.getMessage());
        }
    }

    public Integer saveElement(LostItem item) throws ResponseStatusException {
        final List<String> errors = validateSaveItem(item);
        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(",", errors));
        }
        try {
            return itemRepository.saveElement(item);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwables.getMessage());
        }
    }

    public Optional<LostItem> updateElement(LostItem item) throws ResponseStatusException {
        final List<String> errors = validateUpdateItem(item);
        if (!errors.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.join(",", errors));
        }
        try {
            return itemRepository.updateElement(item);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwables.getMessage());
        }
    }

    public void deleteElementById(Integer id) throws ResponseStatusException {
        if (id == null || id < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }
        try {
            itemRepository.deleteElementById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, throwables.getMessage());
        }
    }

    private List<String> validateSaveItem(LostItem item) {
        List<String> errors = new ArrayList<>();
        if (item.getKeywords() == null) {
            errors.add("Keywords can`t be null");
        }
        if (item.getId() != null) {
            errors.add("Id must be null, use patch instead");
        }
        if (item.getItemName().startsWith("ork")) {
            errors.add("Item name can`t start with \"ork!\"");
        }

        return errors;
    }

    private List<String> validateUpdateItem(LostItem item) {
        List<String> errors = new ArrayList<>();
        if (item.getId() == null || item.getId() < 0) {
            errors.add("Id can`t be null/negative");
        }
        return errors;
    }

}
