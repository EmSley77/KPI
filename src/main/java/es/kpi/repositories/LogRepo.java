package es.kpi.repositories;

import es.kpi.entities.KpiLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepo extends JpaRepository<KpiLog, Long> {
}
