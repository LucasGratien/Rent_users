package edu.campus.numerique.users.Controller;
import edu.campus.numerique.users.model.UserNotFound;
import edu.campus.numerique.users.model.Users;
import edu.campus.numerique.users.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "API users")
@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @Operation(summary = "Récupération de tous les users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Opération réussie",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class),
                            examples = @ExampleObject(
                                    name = "Exemple d'utilisateur",
                                    value = "[{\"id\": 1, \"name\": \"Lucas Gratien\", \"email\": \"lucas.gratien@example.com\", \"age\": 25}]"
                            )
                    )),
            @ApiResponse(responseCode = "400", description = "Requête invalide",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Client non trouvé",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.findAll();
    }

    @Operation(summary = "Récupère un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client trouvé",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class),
                            examples = @ExampleObject(
                                    value = "{\"id\": 1, \"name\": \"Lucas gratien\", \"email\": \"lucas.gratien@example.com\", \"age\": 25}"
                            ))),
            @ApiResponse(responseCode = "404", description = "Client non trouvé",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        Users user = usersService.findById(id).orElseThrow(UserNotFound::new);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Ajoute un nouveau client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client créé avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class),
                            examples = @ExampleObject(
                                    value = "{\"id\": 2, \"name\": \"Lulu gratien\", \"email\": \"lulu.gratien@example.com\", \"age\": 30}"
                            ))),
            @ApiResponse(responseCode = "400", description = "Requête invalide",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        Users createdUser = usersService.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @Operation(summary = "Met à jour un client existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client mis à jour avec succès",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class),
                            examples = @ExampleObject(
                                    value = "{\"id\": 1, \"name\": \"Lucas Gratien\", \"email\": \"lucas.gratien@example.com\", \"age\": 26}"
                            ))),
            @ApiResponse(responseCode = "404", description = "Client non trouvé",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Requête invalide",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users users) {
        return ResponseEntity.ok(usersService.update(id, users));
    }

    @Operation(summary = "Supprime un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Client non trouvé",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        usersService.deleteById(id);
    }
}
