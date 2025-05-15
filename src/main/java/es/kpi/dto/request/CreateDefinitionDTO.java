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
public class CreateDefinitionDTO {


    @NotNull
    @Size(max = 100)
    private String name;

    @Size(max = 20)
    private String unit;

    @NotNull
    @Size(max = 36)
    private String userId;

    @NotNull
    private Double value;

    private Long categoryId; // optional if not always needed

    private Boolean isRecurring;

    @NotNull
    private String recurrenceType; // daily, weekly, monthly, yearly

    @NotNull
    private LocalDate recurrenceDate;  // Meaning depends on recurrenceType
}
