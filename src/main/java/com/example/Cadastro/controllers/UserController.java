package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> findAll() {
        List<User> allUsers = repository.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PostMapping(value = "/post")
    public ResponseEntity<User> postUser(@RequestBody User user){
        User newUser = repository.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/searchName")
    public ResponseEntity<List<User>> findByName(@RequestParam String name) {

        /*
        antes eu tava usando uma função ENORME, sem necessidade alguma, usando collect e mais coisas
        descobri que eu podia criar no userrepoistory uma classe de find
        */

        List<User> usersFiltradosName = repository.findByLoginContaining(name);

        if (!usersFiltradosName.isEmpty()) {
            return new ResponseEntity<>(usersFiltradosName, HttpStatus.OK); //retorna os filtrados
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping(value = "/searchID")
    public ResponseEntity<Optional<User>> findByID(@RequestParam UUID id) {

        //tive que usar o optional, por que indico que tem a possibilidade do valor na oexistir

        Optional<User> usersFiltrados = repository.findById(id);
        
        if (!usersFiltrados.isEmpty()) {
            return new ResponseEntity<>(usersFiltrados, HttpStatus.OK); //retorna os filtrados
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<Void> deleteUser(@RequestParam String name) {

        /*
        tentei utilizar aqui o método searchUser, mas não consigo pois ele
        retorna um responseEntity como valor
         */

        List<User> usersToRemove = repository.findByLoginContaining(name);

        if (usersToRemove.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        repository.deleteAll(usersToRemove); //remove usuários
        return new ResponseEntity<>(HttpStatus.OK);
    }

}