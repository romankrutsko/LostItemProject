package com.example.lostitem.rest;

import com.example.lostitem.items.model.Page;
import com.example.lostitem.rest.model.LostItem;
import com.example.lostitem.rest.service.LostItemRestService.LostItemRestService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequestMapping("/api/v1/")
public class RestApiController extends ResponseEntityExceptionHandler {
    private final LostItemRestService lostElementsService;

    public RestApiController(LostItemRestService lostElementsService) {
        this.lostElementsService = lostElementsService;
    }

    @GetMapping(value = "item", produces = "application/json")
    public ResponseEntity<Page<LostItem>> getItemById(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "5") Integer pageSize, @RequestBody(required = false) LostItem item) {
        return ResponseEntity.ok(lostElementsService.getAllElementsPageable(new Page<>(page, pageSize), item));
    }

    @GetMapping(value = "item/{id}")
    public ResponseEntity<LostItem> getItemById(@PathVariable Integer id) {
        return ResponseEntity.of(lostElementsService.getElementById(id));
    }

    @PostMapping(value = "item")
    public ResponseEntity<String> saveElement(@io.swagger.v3.oas.annotations.parameters.RequestBody
                                                      (description = "element to save", required = true,
                                                              content = @Content(schema = @Schema(implementation = LostItem.class))) LostItem item) {
        int id = lostElementsService.saveElement(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Saved!, Id is " + id);
    }

    @PutMapping(value = "item")
    public ResponseEntity<LostItem> updateElement(@io.swagger.v3.oas.annotations.parameters.RequestBody LostItem item) {
        return ResponseEntity.of(lostElementsService.updateElement(item));
    }

    @DeleteMapping(value = "item/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable Integer id) {
        lostElementsService.deleteElementById(id);
        return ResponseEntity.ok("Deleted");
    }

    @ExceptionHandler(ResponseStatusException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getReason());
    }
}
