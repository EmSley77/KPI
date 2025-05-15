package es.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefinitionResponseDTO {

    private Long id;
    private String KpiDefinitionName;
    private String unit;
    private String userId;
    private Double value;
    private Long categoryId;
    private Boolean isRecurring;
    private String recurrenceType; // daily, weekly, monthly, yearly
    private String recurrenceDate; // date when the recurrence ends (MM-DD)

}
