package backend.project.controllers;

import backend.project.entities.EventType;
import backend.project.services.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event-types")
@CrossOrigin("*")
public class EventTypeController {

    @Autowired
    private EventTypeService eventTypeService;

    @PostMapping
    public ResponseEntity<EventType> createEventType(@RequestBody EventType eventType) {
        EventType savedEventType = eventTypeService.insertEventType(eventType);
        return new ResponseEntity<>(savedEventType, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<EventType> createEventTypeByDetails(@RequestParam String theme, @RequestParam String description, @RequestParam String eventName) {
        EventType savedEventType = eventTypeService.insertEventType(theme, description, eventName);
        return new ResponseEntity<>(savedEventType, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventType(@PathVariable Long id) {
        eventTypeService.deleteEventType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deleteEventTypeForced(@PathVariable Long id, @RequestParam boolean forced) {
        eventTypeService.deleteEventType(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EventType>> listAllEventTypes() {
        List<EventType> eventTypes = eventTypeService.listAllEventTypes();
        if (eventTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventType> getEventTypeById(@PathVariable Long id) {
        EventType eventType = eventTypeService.findById(id);
        return new ResponseEntity<>(eventType, HttpStatus.OK);
    }

    @GetMapping("/theme")
    public ResponseEntity<List<EventType>> getEventTypesByTheme(@RequestParam String theme) {
        List<EventType> eventTypes = eventTypeService.findByTheme(theme);
        if (eventTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventTypes, HttpStatus.OK);
    }
}
