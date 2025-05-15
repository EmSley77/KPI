package es.kpi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * DTO for creating a new category.
 * <p>
 * This class is used to transfer data from the client to the server when creating a new category.
 * It contains the necessary fields and validation annotations to ensure that the data is valid.
 */
public class CreateCategoryDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "User ID is required")
    private String userId;

}
