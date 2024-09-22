package backend.project.repositories;

import backend.project.entities.PurchasedTickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedTicketsRepository extends JpaRepository<PurchasedTickets, Long> {

    @Query("SELECT p FROM PurchasedTickets p WHERE p.transaction.client.id = :clientId")
    List<PurchasedTickets> findByClient(Long clientId);

    @Query("SELECT COUNT(p) FROM PurchasedTickets p WHERE p.ticketType.event.id = :eventId")
    Integer countPurchasedTicketsByEvent(Long eventId);

    @Query("SELECT pt FROM PurchasedTickets pt WHERE pt.transaction.client.id = :clientId")
    List<PurchasedTickets> findByClientId(@Param("clientId") Long clientId);
}
