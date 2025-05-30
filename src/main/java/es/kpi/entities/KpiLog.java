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
@Table(name = "kpi_logs", schema = "kpi")
public class KpiLog {
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
    @Column(name = "date", nullable = false)
    private LocalDate date;

    public KpiLog(KpiDefinition kpi, String userId) {
        this.kpi = kpi;
        this.userId = userId;
        this.date = LocalDate.now();
    }
}