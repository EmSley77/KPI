package es.kpi.services;

import es.kpi.dto.request.CreateLogDTO;
import es.kpi.dto.response.LogResponseDTO;
import es.kpi.entities.KpiLog;
import es.kpi.repositories.LogRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KpiLogService {

    private final LogRepo logRepo;
    private final KpiDefinitionService kpiDefinitionService;


    //create
    public void createLog(CreateLogDTO createLogDTO) {
        logRepo.save(mapToLogEntity(createLogDTO));
    }

    //map to category entity obj
    private KpiLog mapToLogEntity(CreateLogDTO createLogDTO) {
        return new KpiLog(
                kpiDefinitionService.getById(createLogDTO.getKpiDefinitionId()),
                createLogDTO.getUserId());
    }

    //delete
    public void deleteLog(Long logId) {
        logRepo.deleteById(logId);
    }

    //fetch by userId
    public List<LogResponseDTO> getAllLogsByUserId(String userId) {
        return logRepo.findAllByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    private LogResponseDTO mapToResponseDTO(KpiLog kpiLog) {
        return new LogResponseDTO(
                kpiLog.getId(),
                kpiLog.getKpi().getId(),
                kpiLog.getUserId(),
                kpiLog.getDate()
        );
    }
}
