package dom.com.thesisoauth2resourceserver.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ImageDTO {
    private Long id;
    @NotBlank(message = "location is blank")
    private String location;
    @NotBlank(message = "date is blank")
    private String date;
    private Double latitudeDegrees;
    private Double longitudeDegrees;
    private String latitudeHemisphere;
    private String longitudeHemisphere;
    private String annotation;
    @NotBlank(message = "image is blank")
    private String imageBase64;
}
