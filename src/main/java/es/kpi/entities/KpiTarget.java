package es.kpi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@Entity
@Table(name = "kpi_targets", schema = "kpi")
public class KpiTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kpi_id", nullable = false)
    private KpiDefinition kpi;

    @Size(max = 36)
    @NotNull
    @Column(name = "user_id", nullable = false, length = 36)
    private String userId;

    @NotNull
    @Column(name = "target_value", nullable = false)
    private Double targetValue;

    @NotNull
    @Column(name = "target_date", nullable = false)
    private LocalDate targetDate;

    public KpiTarget(KpiDefinition kpi, String userId, Double targetValue, LocalDate targetDate) {
        this.kpi = kpi;
        this.userId = userId;
        this.targetValue = targetValue;
        this.targetDate = targetDate;
    }
}