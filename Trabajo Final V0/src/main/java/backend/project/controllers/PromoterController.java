package backend.project.controllers;

import backend.project.entities.Promoter;
import backend.project.services.PromoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promoters")
@CrossOrigin("*")
public class PromoterController {

    @Autowired
    private PromoterService promoterService;

    @PostMapping
    public ResponseEntity<Promoter> createPromoter(@RequestBody Promoter promoter) {
        Promoter savedPromoter = promoterService.insertPromoter(promoter);
        return new ResponseEntity<>(savedPromoter, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Promoter> createPromoterByDetails(@RequestParam String details) {
        Promoter savedPromoter = promoterService.insertPromoter(details);
        return new ResponseEntity<>(savedPromoter, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromoter(@PathVariable Long id) {
        promoterService.deletePromoter(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deletePromoterForced(@PathVariable Long id, @RequestParam boolean forced) {
        promoterService.deletePromoter(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Promoter>> listAllPromoters() {
        List<Promoter> promoters = promoterService.listAllPromoters();
        if (promoters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(promoters, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promoter> getPromoterById(@PathVariable Long id) {
        Promoter promoter = promoterService.findById(id);
        return new ResponseEntity<>(promoter, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Promoter>> getPromotersByDetails(@RequestParam String keyword) {
        List<Promoter> promoters = promoterService.findByDetailsContaining(keyword);
        if (promoters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(promoters, HttpStatus.OK);
    }
}
