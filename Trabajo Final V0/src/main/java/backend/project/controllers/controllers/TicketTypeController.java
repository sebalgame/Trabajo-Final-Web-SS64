package backend.project.controllers;

import backend.project.entities.TicketType;
import backend.project.services.TicketTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-types")
@CrossOrigin("*")
public class TicketTypeController {

    @Autowired
    private TicketTypeService ticketTypeService;

    @PostMapping
    public ResponseEntity<TicketType> createTicketType(@RequestBody TicketType ticketType) {
        TicketType savedTicketType = ticketTypeService.insertTicketType(ticketType);
        return new ResponseEntity<>(savedTicketType, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<TicketType> createTicketTypeByDetails(@RequestParam String name, @RequestParam Double price,
                                                                @RequestParam Integer availableQuantity, @RequestParam Long eventId) {
        TicketType savedTicketType = ticketTypeService.insertTicketType(name, price, availableQuantity, eventId);
        return new ResponseEntity<>(savedTicketType, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketType(@PathVariable Long id) {
        ticketTypeService.deleteTicketType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deleteTicketTypeForced(@PathVariable Long id, @RequestParam boolean forced) {
        ticketTypeService.deleteTicketType(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<TicketType>> listAllTicketTypes() {
        List<TicketType> ticketTypes = ticketTypeService.listAllTicketTypes();
        if (ticketTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketType> getTicketTypeById(@PathVariable Long id) {
        TicketType ticketType = ticketTypeService.findById(id);
        return new ResponseEntity<>(ticketType, HttpStatus.OK);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<TicketType>> getTicketTypesByEvent(@PathVariable Long eventId) {
        List<TicketType> ticketTypes = ticketTypeService.findByEvent(eventId);
        if (ticketTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ticketTypes, HttpStatus.OK);
    }
}
