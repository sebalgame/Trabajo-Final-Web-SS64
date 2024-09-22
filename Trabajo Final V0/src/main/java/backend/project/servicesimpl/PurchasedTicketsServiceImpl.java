package backend.project.servicesimpl;

import backend.project.entities.PurchasedTickets;
import backend.project.entities.TicketType;
import backend.project.entities.Transaction;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.PurchasedTicketsRepository;
import backend.project.repositories.TicketTypeRepository;
import backend.project.repositories.TransactionRepository;
import backend.project.services.PurchasedTicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchasedTicketsServiceImpl implements PurchasedTicketsService {

    @Autowired
    private PurchasedTicketsRepository purchasedTicketsRepository;

    @Autowired
    private TicketTypeRepository ticketTypeRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public PurchasedTickets insertPurchasedTickets(PurchasedTickets purchasedTickets) {
        return purchasedTicketsRepository.save(purchasedTickets);
    }

    @Override
    public PurchasedTickets insertPurchasedTickets(Double purchasePrice, LocalDateTime purchaseDate, Long ticketTypeId, Long transactionId) {
        PurchasedTickets purchasedTickets = new PurchasedTickets();

        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("TicketType not found with ID: " + ticketTypeId));

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with ID: " + transactionId));

        purchasedTickets.setPurchasePrice(purchasePrice);
        purchasedTickets.setPurchaseDate(purchaseDate);
        purchasedTickets.setTicketType(ticketType);
        purchasedTickets.setTransaction(transaction);

        return purchasedTicketsRepository.save(purchasedTickets);
    }

    @Override
    public void deletePurchasedTickets(Long id) {
        if (!purchasedTicketsRepository.existsById(id)) {
            throw new ResourceNotFoundException("PurchasedTickets not found with ID: " + id);
        }
        purchasedTicketsRepository.deleteById(id);
    }

    @Override
    public void deletePurchasedTickets(Long id, boolean forced) {
        if (!purchasedTicketsRepository.existsById(id)) {
            throw new ResourceNotFoundException("PurchasedTickets not found with ID: " + id);
        }

        if (forced) {
            purchasedTicketsRepository.deleteById(id);
        } else {
            PurchasedTickets purchasedTickets = purchasedTicketsRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("PurchasedTickets not found with ID: " + id));
            purchasedTicketsRepository.delete(purchasedTickets);
        }
    }

    @Override
    public List<PurchasedTickets> listAllPurchasedTickets() {
        return purchasedTicketsRepository.findAll();
    }

    @Override
    public PurchasedTickets findById(Long id) {
        return purchasedTicketsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PurchasedTickets not found with ID: " + id));
    }

    @Override
    public List<PurchasedTickets> findByClient(Long clientId) {
        return purchasedTicketsRepository.findByClientId(clientId);
    }
}
