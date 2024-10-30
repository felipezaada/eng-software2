package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    private User user;
    private User user1;

    @BeforeEach
    void rodarAntes() {

        // instanciei e depois atribui os valores pra gerar o ID automatico

        user = new User();
        user.setName("Felipe");
        user.setLogin("Felipe2024");
        user.setPassword("1428");
        user.setActive(true);

        user1 = new User();
        user1.setName("Eduardo");
        user1.setLogin("Eduardo2024");
        user1.setPassword("1428");
        user1.setActive(true);
    }

    private void verificarID(ResponseEntity<Optional<User>> response, String name){
        assertNotNull(response);
        assertNotNull(response.getBody());
        if(response.getBody().isPresent()){
            User foundUser = response.getBody().get();
            assertEquals(name, foundUser.getName());
        }else{
            fail("Erro");
        }
    }

    private void verificarNome(ResponseEntity<List<User>> response, String name){
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals(name, response.getBody().get(0).getName());
    }

    @Test
    public void findAll() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        ResponseEntity<List<User>> response = userController.findAll();
        verificarNome(response, user.getName());
    }

    @Test
    public void findById() {

        UUID UUID1 = user.getId();
        UUID UUID2 = user1.getId();

        Mockito.when(userRepository.findById(UUID1)).thenReturn(Optional.of(user));
        ResponseEntity<Optional<User>> response = userController.findByID(UUID1);
        verificarID(response, "Felipe");

        Mockito.when(userRepository.findById(UUID2)).thenReturn(Optional.of(user1));
        ResponseEntity<Optional<User>> response2 = userController.findByID(UUID2);
        verificarID(response2, "Eduardo");
    }

    @Test
    public void findByName(){
        Mockito.when(userRepository.findByNameContaining(user.getName())).thenReturn(List.of(user));
        ResponseEntity<List<User>> response = userController.findByName("Felipe");
        verificarNome(response, "Felipe");

        Mockito.when(userRepository.findByNameContaining(user1.getName())).thenReturn(List.of(user1));
        ResponseEntity<List<User>> response2 = userController.findByName("Eduardo");
        verificarNome(response2, "Eduardo");
    }

}