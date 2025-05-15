package es.kpi.services;


import es.kpi.dto.request.CreateCategoryDTO;
import es.kpi.dto.response.CategoryResponseDTO;
import es.kpi.entities.Category;
import es.kpi.exceptions.NotFoundException;
import es.kpi.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {


    private final CategoryRepo categoryRepo;

    //create
    public void createCategory(CreateCategoryDTO createCategoryDTO) {
        categoryRepo.save(mapToCategoryEntity(createCategoryDTO));
    }

    //map to category entity obj
    private Category mapToCategoryEntity(CreateCategoryDTO createCategoryDTO) {
        return new Category(
                createCategoryDTO.getName(),
                createCategoryDTO.getUserId()
        );
    }

    //edit

    //delete
    public void deleteCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
    }

    //fetch by userId
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllCategoriesByUserId(String userId) {
        return categoryRepo.findAllByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    //fetch by name
    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAllCategoriesByUserIdAndName(String userId, String name) {
        return categoryRepo.findAllByUserIdAndName(userId, name)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    private CategoryResponseDTO mapToResponseDTO(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName(), category.getUserId());
    }


    //fetch by id
    public Category getCategoryById(Long categoryId) {
        return categoryRepo.findById(categoryId).orElseThrow(() -> new NotFoundException("Category not found"));
    }

}
