package backend.project.repositories;

import backend.project.entities.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTypeRepository extends JpaRepository<EventType, Long> {

    @Query("SELECT e FROM EventType e WHERE e.theme = :theme")
    List<EventType> findByTheme(String theme);
}
