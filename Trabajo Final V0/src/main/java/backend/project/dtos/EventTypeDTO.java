package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventTypeDTO {

    private Long id;
    private String theme;
    private String description;
    private String eventName;
    private List<EventDTO> events;
}
