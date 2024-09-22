package backend.project.servicesimpl;

import backend.project.entities.EventType;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.EventTypeRepository;
import backend.project.services.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeServiceImpl implements EventTypeService {

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Override
    public EventType insertEventType(EventType eventType) {
        return eventTypeRepository.save(eventType);
    }

    @Override
    public EventType insertEventType(String theme, String description, String eventName) {
        EventType eventType = new EventType();
        eventType.setTheme(theme);
        eventType.setDescription(description);
        eventType.setEventName(eventName);
        return eventTypeRepository.save(eventType);
    }

    @Override
    public void deleteEventType(Long id) {
        if (!eventTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("EventType not found with ID: " + id);
        }
        eventTypeRepository.deleteById(id);
    }

    @Override
    public void deleteEventType(Long id, boolean forced) {
        if (!eventTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("EventType not found with ID: " + id);
        }

        if (forced) {
            eventTypeRepository.deleteById(id);
        } else {
            EventType eventType = eventTypeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("EventType not found with ID: " + id));
            eventTypeRepository.delete(eventType);
        }
    }

    @Override
    public List<EventType> listAllEventTypes() {
        return eventTypeRepository.findAll();
    }

    @Override
    public EventType findById(Long id) {
        return eventTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EventType not found with ID: " + id));
    }

    @Override
    public List<EventType> findByTheme(String theme) {
        return eventTypeRepository.findByTheme(theme);
    }
}
