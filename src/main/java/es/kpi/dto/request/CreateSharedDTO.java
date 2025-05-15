package es.kpi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * DTO for creating a new shared entry.
 * <p>
 * This class is used to transfer data from the client to the server when creating a new shared entry.
 * It contains the necessary fields and validation annotations to ensure that the data is valid.
 */
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
