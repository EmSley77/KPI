package es.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * DTO for KPI definition response.
 * <p>
 * This class is used to transfer data from the server to the client when retrieving a KPI definition.
 * It includes all the necessary fields to represent a KPI definition in the response.
 */
public class LogResponseDTO {

    private Long id;
    private DefinitionResponseDTO kpi_definition;
    private LocalDate date;
}
