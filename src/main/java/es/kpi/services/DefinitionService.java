package es.kpi.services;

import es.kpi.dto.request.CreateDefinitionDTO;
import es.kpi.dto.response.CategoryResponseDTO;
import es.kpi.entities.Category;
import es.kpi.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefinitionService {

    private final CategoryRepo categoryRepo;

    //create
    public void createDefinition(CreateDefinitionDTO createDefinitionDTO) {
        categoryRepo.save(mapToDefinitionRequestDTO(createDefinitionDTO));
    }

    //map to category entity obj
    public Category mapToDefinitionRequestDTO(CreateDefinitionDTO createDefinitionDTO) {
        return new Category(
                createDefinitionDTO.getName(),
                createDefinitionDTO.getUserId()
        );
    }

    //edit

    //delete
    public void deleteCategory(Long categoryId) {
        categoryRepo.deleteById(categoryId);
    }

    //fetch
    public List<CategoryResponseDTO> getAllCategoriesByUserId(String userId) {
        return categoryRepo.findAllByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    //fetch by name
    public List<CategoryResponseDTO> getAllCategoriesByUserIdAndName(String userId, String name) {
        return categoryRepo.findAllByUserIdAndName(userId, name)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }


    public CategoryResponseDTO mapToResponseDTO(Category category) {
        return new CategoryResponseDTO(category.getId(), category.getName(), category.getUserId());
    }
}
