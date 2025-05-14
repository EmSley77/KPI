package es.kpi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateCategoryDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "User ID is required")
    private String userId;

}
