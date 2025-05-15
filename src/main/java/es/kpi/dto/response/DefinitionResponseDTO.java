package es.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * DTO for KPI definition response.
 * <p>
 * This class is used to transfer data from the server to the client when retrieving a KPI definition.
 * It includes all the necessary fields to represent a KPI definition in the response.
 */
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
