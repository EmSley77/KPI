package es.kpi.repositories;

import es.kpi.entities.KpiTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TargetRepo extends JpaRepository<KpiTarget, Long> {
    List<KpiTarget> findAllByUserId(String userId);


    Optional<KpiTarget> findAllByUserIdAndKpiId(String userId, Long kpiId);
}
