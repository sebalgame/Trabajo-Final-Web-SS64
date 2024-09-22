package backend.project.repositories;

import backend.project.entities.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {

    @Query("SELECT t FROM TicketType t WHERE t.event.id = :eventId")
    List<TicketType> findByEvent(Long eventId);

    @Query("SELECT SUM(t.availableQuantity) FROM TicketType t WHERE t.event.id = :eventId")
    Integer findTotalAvailableTicketsByEvent(Long eventId);

    @Query("SELECT tt FROM TicketType tt WHERE tt.event.id = :eventId")
    List<TicketType> findByEventId(@Param("eventId") Long eventId);
}
