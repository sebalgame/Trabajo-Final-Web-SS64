package backend.project.repositories;

import backend.project.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c WHERE c.dni = :dni")
    Client findByDni(String dni);

    @Query("SELECT c FROM Client c WHERE c.age >= :age")
    List<Client> findClientsOlderThan(Integer age);

    @Query("SELECT c FROM Client c WHERE c.phone = :phone")
    List<Client> findByPhone(@Param("phone") String phone);
}
