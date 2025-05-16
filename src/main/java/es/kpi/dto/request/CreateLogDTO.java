package es.kpi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * DTO for creating a new log entry.
 * <p>
 * This class is used to transfer data from the client to the server when creating a new log entry.
 * It contains the necessary fields and validation annotations to ensure that the data is valid.
 */
public class CreateLogDTO {

    @NotNull
    private Long kpiDefinitionId;

}
