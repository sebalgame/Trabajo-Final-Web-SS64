package backend.project.controllers;

import backend.project.dtos.TokenDTO;
import backend.project.dtos.UserDTO;
import backend.project.entities.User;
import backend.project.security.JwtUtilService;
import backend.project.security.SecurityUser;
import backend.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtilService jwtUtilService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.insertUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody UserDTO dtoUser){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoUser.getUsername(),dtoUser.getPassword()));

        TokenDTO dtoToken=new TokenDTO();

        User userFound = userService.findByUsername(dtoUser.getUsername());

        dtoToken.setAuthorities(userFound.getAuthority().getName());
        dtoToken.setJwtToken( jwtUtilService.generateToken(new SecurityUser(userFound)) );

        return new ResponseEntity<>(dtoToken, HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<User> createUserByDetails(@RequestParam String username, @RequestParam String password,
                                                    @RequestParam Boolean active, @RequestParam Long authorityId) {
        User savedUser = userService.insertUser(username, password, active, authorityId);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/force/{id}")
    public ResponseEntity<Void> deleteUserForced(@PathVariable Long id, @RequestParam boolean forced) {
        userService.deleteUser(id, forced);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.listAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<User>> getUsersByActiveStatus(@RequestParam Boolean active) {
        List<User> users = userService.findByActiveStatus(active);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
