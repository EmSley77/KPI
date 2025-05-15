package es.kpi.services;

import es.kpi.dto.request.CreateTargetDTO;
import es.kpi.dto.response.TargetResponseDTO;
import es.kpi.entities.KpiTarget;
import es.kpi.exceptions.NotFoundException;
import es.kpi.repositories.TargetRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KpiTargetService {

    private final TargetRepo targetRepo;
    private final KpiDefinitionService kpiDefinitionService;

    //create
    public void createTarget(CreateTargetDTO createTargetDTO) {
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
        targetRepo.deleteById(targetId);
    }

    //fetch by userId
    public List<TargetResponseDTO> getAllTargetsByUserId(String userId) {
        return targetRepo.findAllByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public TargetResponseDTO getTargetByKpiIdAndUserId(Long KpiId, String userId) {
        return targetRepo.findAllByUserIdAndKpiId(userId,KpiId)
                .filter(kpiTarget -> kpiTarget.getUserId().equals(userId))
                .map(this::mapToResponseDTO)
                .orElseThrow(() -> new NotFoundException("KPI Target not found"));
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
