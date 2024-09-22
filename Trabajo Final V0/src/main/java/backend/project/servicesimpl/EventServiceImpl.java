package backend.project.servicesimpl;

import backend.project.entities.Event;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.EventRepository;
import backend.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event insertEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event insertEvent(Integer capacity, LocalDateTime startDate, LocalDateTime endDate, String address, String accessCode, String image, Long cityId, Long eventTypeId, Long promoterId) {
        Event event = new Event();
        event.setCapacity(capacity);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setAddress(address);
        event.setAccessCode(accessCode);
        event.setImage(image);
        return eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with ID: " + id);
        }
        eventRepository.deleteById(id);
    }

    @Override
    public void deleteEvent(Long id, boolean forced) {
        if (!eventRepository.existsById(id)) {
            throw new ResourceNotFoundException("Event not found with ID: " + id);
        }

        if (forced) {
            eventRepository.deleteById(id);
        } else {
            Event event = eventRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));
            eventRepository.delete(event);
        }
    }

    @Override
    public List<Event> listAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + id));
    }

    @Override
    public List<Event> findByCity(Long cityId) {
        return eventRepository.findByCityId(cityId);
    }
}
