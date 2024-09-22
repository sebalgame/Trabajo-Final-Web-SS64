package backend.project.services;

import backend.project.entities.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

    Event insertEvent(Event event);
    Event insertEvent(Integer capacity, LocalDateTime startDate, LocalDateTime endDate, String address, String accessCode, String image, Long cityId, Long eventTypeId, Long promoterId);
    void deleteEvent(Long id);
    void deleteEvent(Long id, boolean forced);
    List<Event> listAllEvents();
    Event findById(Long id);
    List<Event> findByCity(Long cityId);
}
