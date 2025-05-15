package es.kpi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLogDTO {

    @NotNull
    private Long kpiDefinitionId;

    @NotNull
    @Size(max = 36)
    private String userId;

}
