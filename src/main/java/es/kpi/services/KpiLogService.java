package es.kpi.services;

import es.kpi.dto.request.CreateLogDTO;
import es.kpi.dto.response.DefinitionResponseDTO;
import es.kpi.dto.response.LogResponseDTO;
import es.kpi.entities.KpiDefinition;
import es.kpi.entities.KpiLog;
import es.kpi.repositories.LogRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
/**
 * Service class for managing KPI logs.
 */
public class KpiLogService {

    private final LogRepo logRepo;
    private final KpiDefinitionService kpiDefinitionService;


    //create
    public void createLog(CreateLogDTO createLogDTO) {
        logRepo.save(mapToLogEntity(createLogDTO));
    }

    //map to category entity obj
    private KpiLog mapToLogEntity(CreateLogDTO createLogDTO) {
        KpiDefinition kpiDefinition =  kpiDefinitionService.getById(createLogDTO.getKpiDefinitionId());
        return new KpiLog(
                kpiDefinition,
                kpiDefinition.getUserId());
    }

    //delete
    public void deleteLog(Long logId) {
        if (!logRepo.existsById(logId)) {
            log.warn("KPI Log with ID {} does not exist", logId);
            return;
        }
        logRepo.deleteById(logId);
    }

    //fetch by userId
    public List<LogResponseDTO> getAllLogsByUserId(String userId) {
        return logRepo.findAllByUserId(userId)
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    //get all logs belonging to a specific KPI definition
    public List<LogResponseDTO> getAllLogsByKpiId(KpiDefinition kpi) {
        return logRepo.findByKpiAndUserId(kpi, kpi.getUserId())
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    //fetch all based on filtered KPI definitions DAILY, WEEKLY, MONTHLY, YEARLY
    public List<LogResponseDTO> getLogsByUserIdAndRecurrenceType(String userId, String recurrenceType) {


        List<LogResponseDTO> logs = new ArrayList<>();
        List<DefinitionResponseDTO> kpidefsList = kpiDefinitionService.getKpiDefinitionsByUserIdAndRecurrenceType(userId,
                recurrenceType);
        if (kpidefsList.isEmpty()) {
            log.info("No KPI definitions found for userId: {} and recurrenceType: {}", userId, recurrenceType);
            return List.of();
        }
        kpidefsList.forEach(kpi -> {
            List<LogResponseDTO> logList = getAllLogsByKpiId(kpiDefinitionService.getById(kpi.getId()));
            logs.addAll(logList);
        });
        return logs;

    }

    //fetch all based on filtered KPI definitions and date intervals
    public List<LogResponseDTO> getLogsByUserIdAndRecurrenceTypeAndDateInterval(String userId, String recurrenceType, String timeFrame) {
        List<LogResponseDTO> logs = new ArrayList<>();
        List<DefinitionResponseDTO> kpidefsList = kpiDefinitionService.getKpiDefinitionsByUserIdAndRecurrenceType(userId,
                recurrenceType);

        if (kpidefsList.isEmpty()) {
            log.info("No KPI definitions found for userId: {} and recurrenceType: {}", userId, recurrenceType);
            return List.of();
        }

        LocalDate now = LocalDate.now();
        LocalDate startDate;


        switch (timeFrame) {
            case "TODAY" -> startDate = now;
            case "WEEK" -> startDate = now.minusWeeks(1);
            case "MONTH" -> startDate = now.minusMonths(1);
            case "YEAR" -> startDate = now.minusYears(1);
            case "YTD" -> startDate = LocalDate.of(now.getYear(), 1, 1);
            default -> startDate = now;
        }

        kpidefsList.forEach(kpi -> {
            List<LogResponseDTO> logList = getAllLogsByKpiId(kpiDefinitionService.getById(kpi.getId()));
            List<LogResponseDTO> filteredLogs = logList.stream()
                    .filter(log -> !log.getDate().isBefore(startDate))
                    .toList();
            logs.addAll(filteredLogs);
        });
        return logs;
    }

    public List<LogResponseDTO> getLogsByUserIdAndRecurrenceTypeAndDateIntervalAndKPI(String userId,
                                                                                      String timeFrame,
                                                                                      Long kpiId) {

        KpiDefinition kpiDefinition = kpiDefinitionService.getById(kpiId);

        LocalDate now = LocalDate.now();
        LocalDate startDate;

        switch (timeFrame) {
            case "TODAY" -> startDate = now;
            case "WEEK" -> startDate = now.minusWeeks(1);
            case "MONTH" -> startDate = now.minusMonths(1);
            case "YEAR" -> startDate = now.minusYears(1);
            case "YTD" -> startDate = LocalDate.of(now.getYear(), 1, 1);
            default -> startDate = now;
        }

        List<LogResponseDTO> logList = getAllLogsByKpiId(kpiDefinition);

        List<LogResponseDTO> filteredLogs = logList.stream()
                .filter(log -> !log.getDate().isBefore(startDate))
                .filter(log -> log.getKpi_definition().getUserId().equals(userId)) // if needed
                .toList();

        if (filteredLogs.isEmpty()) {
            log.info("No KPI logs found for userId: {} and timeFrame: {} and kpiId: {}",
                    userId, timeFrame, kpiId);
            return List.of();
        }

        return new ArrayList<>(filteredLogs);
    }

    private LogResponseDTO mapToResponseDTO(KpiLog kpiLog) {
        return new LogResponseDTO(
                kpiLog.getId(),
                kpiDefinitionService.mapToResponseDTO(kpiDefinitionService.getById(kpiLog.getKpi().getId())),
                kpiLog.getDate()
        );
    }
}
