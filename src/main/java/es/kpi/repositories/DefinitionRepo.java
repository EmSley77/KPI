package es.kpi.repositories;

import es.kpi.entities.KpiDefinition;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefinitionRepo extends JpaRepository<KpiDefinition, Long> {

    List<KpiDefinition> findAllByUserId(String userId);

    Optional<KpiDefinition> findByUserIdAndName(String userId, String name);


    boolean existsByUserIdAndName(@NotNull @Size(max = 36) String userId, @NotNull @Size(max = 100) String name);
}
