package es.kpi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSharedDTO {

    @NotNull
    private Long kpiDefinitionId;

    @NotNull
    @Size(max = 36)
    private String ownerId;

    @NotNull
    @Size(max = 36)
    private String sharedWith;
}
