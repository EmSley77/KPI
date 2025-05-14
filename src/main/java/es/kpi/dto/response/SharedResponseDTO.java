package es.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SharedResponseDTO {

    private Long id;
    private Long kpiId;
    private String ownerId;
    private String sharedWith;
}
