package es.kpi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Long categoryId; // optional if not always needed

    private Boolean isRecurring;

    @NotNull
    private String recurrenceType; // daily, weekly, monthly, yearly

    @NotNull
    private String recurrenceDetail;  // Meaning depends on recurrenceType
}
