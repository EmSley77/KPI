package es.kpi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@Entity
@Table(name = "shared_kpis", schema = "kpi")
public class SharedKpi {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "kpi_id", nullable = false)
    private KpiDefinition kpi;

    @Size(max = 36)
    @NotNull
    @Column(name = "owner_id", nullable = false, length = 36)
    private String ownerId;

    @Size(max = 36)
    @NotNull
    @Column(name = "shared_with", nullable = false, length = 36)
    private String sharedWith;

    public SharedKpi(KpiDefinition kpi, String ownerId, String sharedWith) {
        this.kpi = kpi;
        this.ownerId = ownerId;
        this.sharedWith = sharedWith;
    }
}