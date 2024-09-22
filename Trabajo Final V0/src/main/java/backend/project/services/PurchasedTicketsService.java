package backend.project.services;

import backend.project.entities.PurchasedTickets;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchasedTicketsService {

    PurchasedTickets insertPurchasedTickets(PurchasedTickets purchasedTickets);
    PurchasedTickets insertPurchasedTickets(Double purchasePrice, LocalDateTime purchaseDate, Long ticketTypeId, Long transactionId);
    void deletePurchasedTickets(Long id);
    void deletePurchasedTickets(Long id, boolean forced);
    List<PurchasedTickets> listAllPurchasedTickets();
    PurchasedTickets findById(Long id);
    List<PurchasedTickets> findByClient(Long clientId);
}
