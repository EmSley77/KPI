package es.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DefinitionResponseDTO {

    private Long id;
    private String name;
    private String unit;
    private String userId;
    private Long categoryId;
    private Boolean isRecurring;

}
