package es.kpi.repositories;

import es.kpi.entities.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {


    List<Category> findAllByUserId(String userId);
    List<Category> findAllByUserIdAndName(String userId, String name);
}
