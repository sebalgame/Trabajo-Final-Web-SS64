package backend.project.services;

import backend.project.entities.TicketType;

import java.util.List;

public interface TicketTypeService {

    TicketType insertTicketType(TicketType ticketType);
    TicketType insertTicketType(String name, Double price, Integer availableQuantity, Long eventId);
    void deleteTicketType(Long id);
    void deleteTicketType(Long id, boolean forced);
    List<TicketType> listAllTicketTypes();
    TicketType findById(Long id);
    List<TicketType> findByEvent(Long eventId);
}
