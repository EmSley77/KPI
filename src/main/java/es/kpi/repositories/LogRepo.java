package es.kpi.repositories;

import es.kpi.entities.KpiDefinition;
import es.kpi.entities.KpiLog;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface LogRepo extends JpaRepository<KpiLog, Long> {
    List<KpiLog> findAllByUserId(String userId);

    Optional<KpiLog> findByKpi(@NotNull KpiDefinition kpiDefinition);
}
