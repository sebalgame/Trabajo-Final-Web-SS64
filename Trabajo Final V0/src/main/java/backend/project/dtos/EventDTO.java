package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDTO {

    private Long id;
    private Integer capacity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String address;
    private String accessCode;
    private String image;
    private CityDTO city;
    private EventTypeDTO eventType;
    private PromoterDTO promoter;
}
