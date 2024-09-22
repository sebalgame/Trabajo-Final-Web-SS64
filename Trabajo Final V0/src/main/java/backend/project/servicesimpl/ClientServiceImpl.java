package backend.project.servicesimpl;

import backend.project.entities.Client;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.ClientRepository;
import backend.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client insertClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client insertClient(String firstName, String lastName, String gender, Integer age, String phone, String dni, Long userId) {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setGender(gender);
        client.setAge(age);
        client.setPhone(phone);
        client.setDni(dni);
        // Supone que el userId ya está manejado por otro servicio o lógica de negocio
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with ID: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Override
    public void deleteClient(Long id, boolean forced) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with ID: " + id);
        }

        if (forced) {
            clientRepository.deleteById(id);
        } else {
            Client client = clientRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));
            clientRepository.delete(client);
        }
    }

    @Override
    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));
    }

    @Override
    public List<Client> findByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

}
