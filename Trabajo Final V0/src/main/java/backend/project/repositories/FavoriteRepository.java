package backend.project.repositories;

import backend.project.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    @Query("SELECT f FROM Favorite f WHERE f.client.id = :clientId")
    List<Favorite> findByClient(Long clientId);

    @Query("SELECT f FROM Favorite f WHERE f.event.id = :eventId")
    List<Favorite> findByEvent(Long eventId);

    @Query("SELECT f FROM Favorite f WHERE f.client.id = :clientId")
    List<Favorite> findByClientId(@Param("clientId") Long clientId);
}
