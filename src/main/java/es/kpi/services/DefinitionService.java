package es.kpi.services;

import es.kpi.dto.request.CreateDefinitionDTO;
import es.kpi.dto.response.DefinitionResponseDTO;
import es.kpi.entities.KpiDefinition;
import es.kpi.exceptions.NotFoundException;
import es.kpi.repositories.DefinitionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefinitionService {

    private final DefinitionRepo definitionRepo;
    private final CategoryService categoryService;

    //create
    public void createDefinition(CreateDefinitionDTO createDefinitionDTO) {
        definitionRepo.save(mapToDefinitionRequestDTO(createDefinitionDTO));
    }

    //map to category entity obj
    private KpiDefinition mapToDefinitionRequestDTO(CreateDefinitionDTO createDefinitionDTO) {
        return new KpiDefinition(
                createDefinitionDTO.getName(),
                createDefinitionDTO.getUnit(),
                createDefinitionDTO.getUserId(),
                categoryService.getCategoryById(createDefinitionDTO.getCategoryId()),
                createDefinitionDTO.getIsRecurring());
    }

    //edit

    //delete
    public void deleteCategory(Long definitionId) {
        definitionRepo.deleteById(definitionId);
    }

    //fetch
    @Transactional(readOnly = true)
    public List<DefinitionResponseDTO> getAllCategoriesByUserId(String userId) {
        return definitionRepo.findAllByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    //fetch by name and userId
    @Transactional(readOnly = true)
    public DefinitionResponseDTO getKPIDefinitionByNameAndUserId(String userId, String name) {
        return definitionRepo.findByUserIdAndName(userId, name)
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> new NotFoundException("KPI Definition not found"));
    }

    //fetch by id
    @Transactional(readOnly = true)
    public KpiDefinition getById(Long id) {
        return definitionRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("KPI Definition not found"));
    }


    private DefinitionResponseDTO mapToResponseDTO(KpiDefinition definition) {
        return new DefinitionResponseDTO(
                definition.getId(),
                definition.getName(),
                definition.getUnit(),
                definition.getUserId(),
                definition.getCategory().getId(),
                definition.getIsRecurring()
        );
    }
}
