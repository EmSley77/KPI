package es.kpi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * DTO for representing a category response.
 * <p>
 * This class is used to transfer data from the server to the client when retrieving a category.
 * It contains the necessary fields to represent a category response.
 */
public class CategoryResponseDTO {

    private Long id;
    private String categoryName;
    private String userId;


}
