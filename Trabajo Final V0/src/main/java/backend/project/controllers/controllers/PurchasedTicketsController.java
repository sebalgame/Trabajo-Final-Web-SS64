package backend.project.controllers;

import backend.project.entities.PurchasedTickets;
import backend.project.services.PurchasedTicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/purchased-tickets")
@CrossOrigin("*")
public class PurchasedTicketsController {

    @Autowired
    private PurchasedTicketsService purchasedTicketsService;

    @PostMapping
    public ResponseEntity<PurchasedTickets> createPurchasedTickets(@RequestBody PurchasedTickets purchasedTickets) {
        PurchasedTickets savedPurchasedTickets = purchasedTicketsService.insertPurchasedTickets(purchasedTickets);
        return new ResponseEntity<>(savedPurchasedTickets, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<PurchasedTickets> createPurchasedTicketsByDetails(@RequestParam Double purchasePrice, @RequestParam LocalDateTime purchaseDate,
                                                                            @RequestParam Long ticketTypeId, @RequestParam Long transactionId) {
        PurchasedTickets savedPurchasedTickets = purchasedTicketsService.insertPurchasedTickets(purchasePrice, purchaseDate, ticketTypeId, transactionId);
        return new ResponseEntity<>(savedPurchasedTickets, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchasedTickets(@PathVariable Long id) {
        purchasedTicketsService.deletePurchasedTickets(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deletePurchasedTicketsForced(@PathVariable Long id, @RequestParam boolean forced) {
        purchasedTicketsService.deletePurchasedTickets(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<PurchasedTickets>> listAllPurchasedTickets() {
        List<PurchasedTickets> purchasedTickets = purchasedTicketsService.listAllPurchasedTickets();
        if (purchasedTickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(purchasedTickets, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchasedTickets> getPurchasedTicketsById(@PathVariable Long id) {
        PurchasedTickets purchasedTickets = purchasedTicketsService.findById(id);
        return new ResponseEntity<>(purchasedTickets, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<PurchasedTickets>> getPurchasedTicketsByClient(@PathVariable Long clientId) {
        List<PurchasedTickets> purchasedTickets = purchasedTicketsService.findByClient(clientId);
        if (purchasedTickets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(purchasedTickets, HttpStatus.OK);
    }
}
