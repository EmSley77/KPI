package es.kpi.repositories;

import es.kpi.entities.KpiDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefinitionRepo extends JpaRepository<KpiDefinition, Long> {

    List<KpiDefinition> findAllByUserId(String userId);

    Optional<KpiDefinition> findByUserIdAndName(String userId, String name);


}
