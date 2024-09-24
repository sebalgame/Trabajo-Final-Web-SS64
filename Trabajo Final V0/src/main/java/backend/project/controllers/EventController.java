package backend.project.controllers;

import backend.project.entities.Event;
import backend.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin("*")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event savedEvent = eventService.insertEvent(event);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEventByDetails(@RequestParam Integer capacity, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate,
                                                      @RequestParam String address, @RequestParam String accessCode, @RequestParam String image,
                                                      @RequestParam Long cityId, @RequestParam Long eventTypeId, @RequestParam Long promoterId) {
        Event savedEvent = eventService.insertEvent(capacity, startDate, endDate, address, accessCode, image, cityId, eventTypeId, promoterId);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deleteEventForced(@PathVariable Long id, @RequestParam boolean forced) {
        eventService.deleteEvent(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Event>> listAllEvents() {
        List<Event> events = eventService.listAllEvents();
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.findById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<Event>> getEventsByCity(@PathVariable Long cityId) {
        List<Event> events = eventService.findByCity(cityId);
        if (events.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event eventDetails) {
        Event event = eventService.findById(id);

        event.setCapacity(eventDetails.getCapacity());
        event.setStartDate(eventDetails.getStartDate());
        event.setEndDate(eventDetails.getEndDate());
        event.setAddress(eventDetails.getAddress());
        event.setAccessCode(eventDetails.getAccessCode());
        event.setImage(eventDetails.getImage());

        Event updatedEvent = eventService.insertEvent(event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }
}