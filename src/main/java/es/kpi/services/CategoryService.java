package es.kpi.services;


import es.kpi.dto.request.CreateCategoryDTO;
import es.kpi.dto.response.CategoryResponseDTO;
import es.kpi.entities.Category;
import es.kpi.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
/**
 * Service class for managing categories.
 */
public class CategoryService {


    private final CategoryRepo categoryRepo;

    public void deleteCategory(Long categoryId) {
        if (!categoryRepo.existsById(categoryId)) {
            log.warn("Category with ID {} does not exist", categoryId);
            return;
        }
        categoryRepo.deleteById(categoryId);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllCategoriesByUserId(String userId) {
        List<Category> categories = categoryRepo.findAllByUserId(userId);
        if (categories.isEmpty()) {
            log.info("No categories found for userId: {}", userId);
            return List.of();
        }
        return categories.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepo.findById(categoryId)
                .orElseGet(() -> {
                    log.info("Category with ID {} not found, returning empty object", categoryId);
                    return new Category();
                });
    }

    @Transactional(readOnly = true)
    public CategoryResponseDTO getAllCategoriesByUserIdAndName(String userId, String name) {
        Category category = categoryRepo.findAllByUserIdAndName(userId, name)
                .orElse(new Category());
        return mapToResponseDTO(category);
    }

    //create category
    public void createCategory(CreateCategoryDTO createCategoryDTO) {
        if (categoryRepo.findAllByUserIdAndName(createCategoryDTO.getUserId(), createCategoryDTO.getName()).isPresent()) {
            log.warn("Category with name {} already exists for userId: {}", createCategoryDTO.getName(), createCategoryDTO.getUserId());
            return;
        }


        categoryRepo.save(mapToCategoryEntity(createCategoryDTO));
    }

    //update category
    public void updateCategory(Long categoryId, CreateCategoryDTO createCategoryDTO) {
        if (!categoryRepo.existsById(categoryId)) {
            log.warn("Category with ID {} does not exist", categoryId);
            return;
        }
        Category category = getCategoryById(categoryId);
        category.setName(createCategoryDTO.getName());
        categoryRepo.save(category);
    }

    //map to category entity obj
    private Category mapToCategoryEntity(CreateCategoryDTO createCategoryDTO) {
        return new Category(
                createCategoryDTO.getName(),
                createCategoryDTO.getUserId()
        );
    }

    //map to category response obj
    private CategoryResponseDTO mapToResponseDTO(Category category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getUserId()
        );
    }

}
