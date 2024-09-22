package backend.project.repositories;

import backend.project.entities.Promoter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoterRepository extends JpaRepository<Promoter, Long> {

    @Query("SELECT p FROM Promoter p WHERE p.details LIKE %:keyword%")
    List<Promoter> findByDetailsContaining(String keyword);
}
