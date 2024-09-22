package backend.project.servicesimpl;

import backend.project.entities.Client;
import backend.project.entities.Event;
import backend.project.entities.Favorite;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.ClientRepository;
import backend.project.repositories.EventRepository;
import backend.project.repositories.FavoriteRepository;
import backend.project.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public Favorite insertFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Favorite insertFavorite(Long clientId, Long eventId) {
        Favorite favorite = new Favorite();

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + clientId));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + eventId));

        favorite.setClient(client);
        favorite.setEvent(event);

        return favoriteRepository.save(favorite);
    }

    @Override
    public void deleteFavorite(Long id) {
        if (!favoriteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Favorite not found with ID: " + id);
        }
        favoriteRepository.deleteById(id);
    }

    @Override
    public void deleteFavorite(Long id, boolean forced) {
        if (!favoriteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Favorite not found with ID: " + id);
        }

        if (forced) {
            favoriteRepository.deleteById(id);
        } else {
            Favorite favorite = favoriteRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with ID: " + id));
            favoriteRepository.delete(favorite);
        }
    }

    @Override
    public List<Favorite> listAllFavorites() {
        return favoriteRepository.findAll();
    }

    @Override
    public Favorite findById(Long id) {
        return favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with ID: " + id));
    }

    @Override
    public List<Favorite> findByClient(Long clientId) {
        return favoriteRepository.findByClientId(clientId);
    }
}
