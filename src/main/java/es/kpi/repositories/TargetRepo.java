package es.kpi.repositories;

import es.kpi.entities.KpiTarget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetRepo extends JpaRepository<KpiTarget, Long> {
}
