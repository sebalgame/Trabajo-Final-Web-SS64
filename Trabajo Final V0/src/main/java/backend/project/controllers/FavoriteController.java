package backend.project.controllers;

import backend.project.entities.Favorite;
import backend.project.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin("*")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite) {
        Favorite savedFavorite = favoriteService.insertFavorite(favorite);
        return new ResponseEntity<>(savedFavorite, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Favorite> createFavoriteByDetails(@RequestParam Long clientId, @RequestParam Long eventId) {
        Favorite savedFavorite = favoriteService.insertFavorite(clientId, eventId);
        return new ResponseEntity<>(savedFavorite, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        favoriteService.deleteFavorite(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deleteFavoriteForced(@PathVariable Long id, @RequestParam boolean forced) {
        favoriteService.deleteFavorite(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Favorite>> listAllFavorites() {
        List<Favorite> favorites = favoriteService.listAllFavorites();
        if (favorites.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable Long id) {
        Favorite favorite = favoriteService.findById(id);
        return new ResponseEntity<>(favorite, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Favorite>> getFavoritesByClient(@PathVariable Long clientId) {
        List<Favorite> favorites = favoriteService.findByClient(clientId);
        if (favorites.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }
}
