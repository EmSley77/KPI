package es.kpi.repositories;

import es.kpi.entities.KpiDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefinitionRepo extends JpaRepository<KpiDefinition, Long> {
}
