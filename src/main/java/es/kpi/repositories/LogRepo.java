package es.kpi.repositories;

import es.kpi.entities.KpiDefinition;
import es.kpi.entities.KpiLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LogRepo extends JpaRepository<KpiLog, Long> {
    List<KpiLog> findAllByUserId(String userId);

    List<KpiLog> findByKpiAndUserId(KpiDefinition kpiDefinition, String userId);

    Optional<KpiLog> findKpiLogByKpiAndUserId(KpiDefinition kpiDefinition, String userId);
}
