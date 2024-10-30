package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Operation(description = "Busca todos os usuários presents no banco de dados.")
    @ApiResponse(responseCode = "200", description = "Retorna os usuários")
    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> findAll() {
        List<User> allUsers = repository.findAll();
        if (allUsers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Retorna 204 se não houver usuários
        }
        return new ResponseEntity<>(allUsers, HttpStatus.OK); // Retorna 200
    }

    @Operation(description = "Salva novo usuário no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário é salvo no banco de dados."),
            @ApiResponse(responseCode = "400", description = "Dados do usuário não são válidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.")
    })
    @PostMapping(value = "/post")
    public ResponseEntity<User> postUser(@Valid @RequestBody User user) {
        try {
            User newUser = repository.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED); // Retorna 201, criado
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build(); // Retorna 400 caso tenha erro nos dados
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Retorna 500 em caso de servidor
        }
    }

    @Operation(description = "Busca usuários pelo nome.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados."),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado com o nome informado.")
    })
    @GetMapping(value = "/searchName")
    public ResponseEntity<List<User>> findByName(@RequestParam String name) {

        List<User> usersFiltradosName = repository.findByNameContaining(name);

        if (!usersFiltradosName.isEmpty()) {
            return new ResponseEntity<>(usersFiltradosName, HttpStatus.OK); // Retorna 200
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 not found
        }
    }

    @Operation(description = "Busca usuários pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados."),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado com o ID informado.")
    })
    @GetMapping(value = "/searchID")
    public ResponseEntity<Optional<User>> findByID(@RequestParam UUID id) {

        Optional<User> usersFiltrados = repository.findById(id);

        if (!usersFiltrados.isEmpty()) {
            return new ResponseEntity<>(usersFiltrados, HttpStatus.OK); // Retorna 200
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 not found
        }
    }

    @Operation(description = "Remove usuários pelo login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado com o login informado.")
    })
    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam String login) {

        List<User> usersToRemove = repository.findByLoginContaining(login);

        if (usersToRemove.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 not found
        }
        repository.deleteAll(usersToRemove); // Remove os usuários
        return new ResponseEntity<>(HttpStatus.OK); // Retorna 200
    }

}