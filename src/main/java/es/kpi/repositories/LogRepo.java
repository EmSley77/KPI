package es.kpi.repositories;

import es.kpi.entities.KpiLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LogRepo extends JpaRepository<KpiLog, Long> {
    List<KpiLog> findAllByUserId(String userId);

}
