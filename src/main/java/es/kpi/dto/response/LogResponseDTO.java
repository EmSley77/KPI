package es.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogResponseDTO {

    private Long id;
    private Long kpiDefinitionId;
    private String userId;
    private LocalDate date;
    private Double value;
    private String note;
}
