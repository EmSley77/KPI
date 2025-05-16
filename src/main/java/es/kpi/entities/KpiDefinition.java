package es.kpi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @NotNull
    @Column(name = "value", nullable = false)
    private Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ColumnDefault("0")
    @Column(name = "is_recurring")
    private Boolean isRecurring;

    @NotNull
    @Column(name = "recurrence_type", nullable = false)
    private String recurrenceType; // daily, weekly, monthly, yearly in caps

    @NotNull
    @Column(name = "recurrence_date", nullable = false)
    private LocalDate recurrenceDate; // day when the recurrence happens (DD)

    public KpiDefinition(String name, String unit, String userId, Double value, Category category,
                         Boolean isRecurring, String recurrenceType, LocalDate recurrenceDate) {
        this.name = name;
        this.unit = unit;
        this.userId = userId;
        this.value = value;
        this.category = category;
        this.isRecurring = isRecurring;
        this.recurrenceType = recurrenceType;
        this.recurrenceDate = recurrenceDate;
    }
}