package es.kpi.background;

import es.kpi.services.BackgroundService;
import es.kpi.services.KpiDefinitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledBackgroundService {

    private final BackgroundService backgroundService;

    // This method will run every day at 1 AM
    @Scheduled(cron = "0 0 1 * * *")
    public void runBackgroundService() {
        backgroundService.runRecurringTasks();
    }

}
