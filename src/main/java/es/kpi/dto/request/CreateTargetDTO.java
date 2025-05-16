package es.kpi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * DTO for creating a new target.
 * <p>
 * This class is used to transfer data from the client to the server when creating a new target.
 * It contains the necessary fields and validation annotations to ensure that the data is valid.
 */
public class CreateTargetDTO {

    @NotNull
    private Long kpiDefinitionId;


    @NotNull
    private Double targetValue;

    @NotNull
    private LocalDate targetDate;
}
