package es.kpi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
@Entity
@Table(name = "kpi_definitions", schema = "kpi")
public class KpiDefinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Size(max = 20)
    @Column(name = "unit", length = 20)
    private String unit;

    @Size(max = 36)
    @NotNull
    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ColumnDefault("0")
    @Column(name = "is_recurring")
    private Boolean isRecurring;


    public KpiDefinition(String name, String unit, String userId, Category category, Boolean isRecurring) {
        this.name = name;
        this.unit = unit;
        this.userId = userId;
        this.category = category;
        this.isRecurring = isRecurring;
    }
}