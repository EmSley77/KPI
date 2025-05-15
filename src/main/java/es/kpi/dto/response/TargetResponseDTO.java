package es.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TargetResponseDTO {

    private Long id;
    private Long kpiDefinitionId;
    private String userId;
    private Double targetValue;
    private LocalDate targetDate;
}
