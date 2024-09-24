package backend.project.controllers;

import backend.project.entities.Client;
import backend.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client savedClient = clientService.insertClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Client> createClientByDetails(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String gender,
                                                        @RequestParam Integer age, @RequestParam String phone, @RequestParam String dni, @RequestParam Long userId) {
        Client savedClient = clientService.insertClient(firstName, lastName, gender, age, phone, dni, userId);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deleteClientForced(@PathVariable Long id, @RequestParam boolean forced) {
        clientService.deleteClient(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Client>> listAllClients() {
        List<Client> clients = clientService.listAllClients();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.findById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @GetMapping("/phone")
    public ResponseEntity<List<Client>> getClientByPhone(@RequestParam String phone) {
        List<Client> clients = clientService.findByPhone(phone);
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        Client client = clientService.findById(id);

        client.setFirstName(clientDetails.getFirstName());
        client.setLastName(clientDetails.getLastName());
        client.setGender(clientDetails.getGender());
        client.setAge(clientDetails.getAge());
        client.setPhone(clientDetails.getPhone());
        client.setDni(clientDetails.getDni());

        Client updatedClient = clientService.insertClient(client);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }
}
