package es.kpi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * DTO for creating a new KPI definition.
 * <p>
 * This class is used to transfer data from the client to the server when creating a new KPI definition.
 * It includes validation annotations to ensure that the data meets certain criteria before being processed.
 */
public class CreateDefinitionDTO {


    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 20)
    private String unit;

    @NotNull
    private Double value;

    private Long categoryId; // optional if not always needed

    private Boolean isRecurring;

    @NotNull
    private String recurrenceType; // daily, weekly, monthly, yearly

    @NotNull
    private LocalDate recurrenceDate;  // Meaning depends on recurrenceType
}
