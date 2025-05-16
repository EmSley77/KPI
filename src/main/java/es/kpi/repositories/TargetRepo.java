package es.kpi.repositories;

import es.kpi.entities.KpiDefinition;
import es.kpi.entities.KpiTarget;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TargetRepo extends JpaRepository<KpiTarget, Long> {
    List<KpiTarget> findAllByUserId(String userId);


    Optional<KpiTarget> findByUserIdAndKpi_Id(String userId, Long kpiId);

    Optional<KpiTarget> findByKpiAndUserId(KpiDefinition kpiDefinition, String userId);

    KpiDefinition kpi(@NotNull KpiDefinition kpi);

    boolean existsByKpi_Id( @NotNull Long kpiDefinitionId);
}
