package backend.project.repositories;

import backend.project.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.startDate >= :startDate AND e.endDate <= :endDate")
    List<Event> findEventsBetweenDates(LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT e FROM Event e WHERE e.city.id = :cityId")
    List<Event> findByCity(Long cityId);

    @Query("SELECT e FROM Event e WHERE e.city.id = :cityId")
    List<Event> findByCityId(@Param("cityId") Long cityId);
}
