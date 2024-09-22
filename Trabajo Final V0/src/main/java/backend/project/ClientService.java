package backend.project.services;

import backend.project.entities.Client;

import java.util.List;

public interface ClientService {

    Client insertClient(Client client);
    Client insertClient(String firstName, String lastName, String gender, Integer age, String phone, String dni, Long userId);
    void deleteClient(Long id);
    void deleteClient(Long id, boolean forced);
    List<Client> listAllClients();
    Client findById(Long id);
    List<Client> findByPhone(String phone);
}
