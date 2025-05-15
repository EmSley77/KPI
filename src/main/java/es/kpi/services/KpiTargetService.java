package es.kpi.services;

import es.kpi.dto.request.CreateTargetDTO;
import es.kpi.dto.response.TargetGoalDTO;
import es.kpi.dto.response.TargetResponseDTO;
import es.kpi.entities.KpiDefinition;
import es.kpi.entities.KpiTarget;
import es.kpi.exceptions.NotFoundException;
import es.kpi.repositories.TargetRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
/**
 * Service class for managing KPI targets.
 */
public class KpiTargetService {

    private final TargetRepo targetRepo;
    private final KpiDefinitionService kpiDefinitionService;
    private final KpiLogService kpiLogService;

    //create
    public void createTarget(CreateTargetDTO createTargetDTO) {
        // Check if target already exists
        if (targetRepo.existsByUserIdAndKpi_Id(createTargetDTO.getUserId(), createTargetDTO.getKpiDefinitionId())) {
            throw new IllegalArgumentException("Target already exists");
        }
        // Check if the KPI definition exists
        KpiDefinition kpiDefinition = kpiDefinitionService.getById(createTargetDTO.getKpiDefinitionId());
        if (kpiDefinition == null) {
            throw new NotFoundException("KPI Definition not found");
        }
        targetRepo.save(mapToTargetEntity(createTargetDTO));
    }

    //map to category entity obj
    private KpiTarget mapToTargetEntity(CreateTargetDTO createTargetDTO) {
        return new KpiTarget(
                kpiDefinitionService.getById(createTargetDTO.getKpiDefinitionId()),
                createTargetDTO.getUserId(),
                createTargetDTO.getTargetValue(),
                createTargetDTO.getTargetDate());
    }


    //delete
    public void deleteTarget(Long targetId) {
        if (!targetRepo.existsById(targetId)) {
            log.warn("KPI Target with ID {} does not exist", targetId);
            return;
        }
        targetRepo.deleteById(targetId);
    }

    //fetch by userId
    public List<TargetResponseDTO> getAllTargetsByUserId(String userId) {
        List<KpiTarget> targets = targetRepo.findAllByUserId(userId);
        if (targets.isEmpty()) {
            log.info("No KPI targets found for userId: {}", userId);
            return List.of();
        }
        return targets
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public TargetResponseDTO getTargetByKpiIdAndUserId(Long KpiId, String userId) {
        return targetRepo.findByUserIdAndKpi_Id(userId, KpiId)
                .filter(kpiTarget -> kpiTarget.getUserId().equals(userId))
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> new NotFoundException("KPI Target not found"));
    }

    //get all targets belonging to a specific KPI definition
    public TargetGoalDTO getTargetGoalByKPI(Long KpiId) {

        KpiDefinition kpiDefinition = kpiDefinitionService.getById(KpiId);

        if (kpiDefinition == null) {
            return null;
        }

        KpiTarget target = targetRepo.findByKpiAndUserId(kpiDefinition, kpiDefinition.getUserId())
                .orElse(null);

        if (target == null) {
            return null;
        }

        String KpiDefinitionName = kpiDefinition.getName();

        //current value
        double currentValue = kpiLogService.getAllLogsByKpiId(kpiDefinition)
                .stream()
                .mapToDouble(log -> kpiDefinition.getValue())
                .sum();

        //target value
        double targetValue = target.getTargetValue();

        String targetDate = target.getTargetDate().toString();
        String currentDate = LocalDate.now().toString();

        boolean isAchieved = currentValue >= targetValue;

        double percentageReached = (currentValue / targetValue) * 100;
        double rounded = Math.round(percentageReached * 10) / 10.0;

        return new TargetGoalDTO(kpiDefinition.getUserId(),
                kpiDefinition.getId(),
                kpiDefinition.getCategory().getName(),
                KpiDefinitionName,
                currentValue,
                targetValue,
                currentDate,
                targetDate,
                rounded,
                isAchieved
        );


    }

    private TargetResponseDTO mapToResponseDTO(KpiTarget kpiTarget) {
        return new TargetResponseDTO(
                kpiTarget.getId(),
                kpiTarget.getKpi().getId(),
                kpiTarget.getUserId(),
                kpiTarget.getTargetValue(),
                kpiTarget.getTargetDate()
        );
    }


}
