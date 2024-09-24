package backend.project.controllers;

import backend.project.entities.Authority;
import backend.project.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authorities")
@CrossOrigin("*")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    @PostMapping
    public ResponseEntity<Authority> createAuthority(@RequestBody Authority authority) {
        Authority savedAuthority = authorityService.insertAuthority(authority);
        return new ResponseEntity<>(savedAuthority, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ResponseEntity<Authority> createAuthorityByName(@RequestParam String name) {
        Authority savedAuthority = authorityService.insertAuthority(name);
        return new ResponseEntity<>(savedAuthority, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthority(@PathVariable Long id) {
        authorityService.deleteAuthority(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deleteAuthorityForced(@PathVariable Long id, @RequestParam boolean forced) {
        authorityService.deleteAuthority(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Authority>> listAllAuthorities() {
        List<Authority> authorities = authorityService.listAllAuthorities();
        if (authorities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(authorities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Authority> getAuthorityById(@PathVariable Long id) {
        Authority authority = authorityService.findById(id);
        return new ResponseEntity<>(authority, HttpStatus.OK);
    }
}
