package es.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * DTO for transferring target data.
 * <p>
 * This class is used to transfer data from the server to the client when retrieving target information.
 * It includes fields that represent the target's properties and their corresponding getters and setters.
 */
public class TargetResponseDTO {

    private Long id;
    private Long kpiDefinitionId;
    private String userId;
    private Double targetValue;
    private LocalDate targetDate;
}
