package backend.project.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer capacity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String address;
    private String accessCode;
    private String image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "event_type_id")
    private EventType eventType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "promoter_id")
    private Promoter promoter;
}
