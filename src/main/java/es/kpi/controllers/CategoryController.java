package es.kpi.controllers;

import es.kpi.dto.request.CreateCategoryDTO;
import es.kpi.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles all HTTP requests related to KPI categories.
 * Includes create, fetch (by user ID or name), and delete operations.
 */
@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    // Create a new KPI category
    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CreateCategoryDTO dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            categoryService.createCategory(dto);
            response.put("data", "KPI category created successfully");
            response.put("error", null);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

    // Get all categories by user ID
    @GetMapping("/all/userId/{userId}")
    public ResponseEntity<?> getAllByUserId(@PathVariable String userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", categoryService.getAllCategoriesByUserId(userId));
            response.put("error", null);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

    // Get all categories by user ID and category name
    @GetMapping("/all/userId/categoryName/{userId}/{categoryName}")
    public ResponseEntity<?> getAllByUserIdAndCategoryName(@PathVariable String userId,
                                                           @PathVariable String categoryName) {
        Map<String, Object> response = new HashMap<>();
        try {
            response.put("data", categoryService.getAllCategoriesByUserIdAndName(userId, categoryName));
            response.put("error", null);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }

    //Update a category
    @PutMapping("/update/categoryId/{categoryId}")
    public ResponseEntity<?> updateCategory(@PathVariable Long categoryId,
                                            @RequestBody CreateCategoryDTO dto) {
        Map<String, Object> response = new HashMap<>();
        try {
            categoryService.updateCategory(categoryId, dto);
            response.put("data", "KPI category updated successfully");
            response.put("error", null);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }


    // Delete a category by ID
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        Map<String, Object> response = new HashMap<>();
        try {
            categoryService.deleteCategory(categoryId);
            response.put("data", "KPI category deleted successfully");
            response.put("error", null);
        } catch (Exception e) {
            response.put("data", null);
            response.put("error", Map.of("message", e.getMessage()));
        }
        return ResponseEntity.ok(response);
    }
}
