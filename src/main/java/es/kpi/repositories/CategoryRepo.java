package es.kpi.repositories;

import es.kpi.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {


    List<Category> findAllByUserId(String userId);

    Optional<Category> findAllByUserIdAndName(String userId, String name);
}
