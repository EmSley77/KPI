package es.kpi.repositories;

import es.kpi.entities.SharedKpi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SharedKpiRepo extends JpaRepository<SharedKpi, Long> {
}
