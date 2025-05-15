package es.kpi.services;

import es.kpi.entities.KpiLog;
import es.kpi.repositories.DefinitionRepo;
import es.kpi.repositories.LogRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
/**
 * BackgroundService is a service that handles background tasks.
 * It is annotated with @Service to indicate that it is a Spring service.
 * The class is also annotated with @Slf4j to enable logging.
 */
public class BackgroundService {

    private final KpiDefinitionService kpiDefinitionService;
    private final DefinitionRepo definitionRepo;
    private final LogRepo logRepo;
    private final KpiLogService kpiLogService;


    /**
     * This method is used to run a background task.
     * It retrieves all definitions from the definition repository and logs them.
     */
    public void runRecurringTasks() {
        log.info("Running background task");
        definitionRepo.findAll().forEach(definition -> {

            if (!definition.getIsRecurring()) {
                log.info("Non-recurring definition: {}", definition);
                return;
            }


            log.info("Recurring definition: {}", definition);
            // Perform the recurring task here
            // For example, you can log the definition or perform some other action


            boolean shouldRunToday = switch (definition.getRecurrenceType()) {
                case "DAILY" -> true;
                case "WEEKLY" -> LocalDate.now().getDayOfWeek().equals(definition.getRecurrenceDate().getDayOfWeek());
                case "MONTHLY" -> LocalDate.now().getDayOfMonth() == definition.getRecurrenceDate().getDayOfMonth();
                case "YEARLY" -> LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("dd-MM"))
                        .equals(definition.getRecurrenceDate()
                                .format(DateTimeFormatter.ofPattern("dd-MM")));
                default -> false;
            };

            if (!shouldRunToday) return;


            KpiLog kpiLog = logRepo.findKpiLogByKpiAndUserId(definition, definition.getUserId()).orElse(null);

            if (kpiLog == null || kpiLog.getDate().isBefore(LocalDate.now())) {
                logRepo.save(new KpiLog(definition, definition.getUserId()));
            } else {
                log.info("Log already exists for this date: {}", kpiLog);
            }

        });
    }
}
