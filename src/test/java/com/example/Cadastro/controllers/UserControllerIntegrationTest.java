package com.example.Cadastro.controllers;

import com.example.Cadastro.models.User;
import com.example.Cadastro.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void rodarAntes() {
        userRepository.deleteAll();

        user = new User();
        user.setName("Felipe");
        user.setLogin("Felipe2024");
        user.setPassword("123456");
        user.setActive(true);
        userRepository.save(user);
    }

    @Test
    void testFindAllUsers() throws Exception {
        mockMvc.perform(get("/user/all"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindUserById() throws Exception {
        mockMvc.perform(get("/user/searchID").param("id", user.getId().toString()))
                .andExpect(status().isOk());
    }

    @Test
    void testFindUserByName() throws Exception {
        mockMvc.perform(get("/user/searchName").param("name", user.getName()))
                .andExpect(status().isOk());
    }

    @Test
    void testPostUsers() throws Exception {
        String userJSON = "{\"name\":\"Felipe Eduardo\", \"login\":\"Felipe2025\", \"password\":\"123456\", \"active\":true}";
        mockMvc.perform(post("/user/post")
                        .contentType(MediaType.APPLICATION_JSON) // falar que é JSON (quebrei a cabeça dms nisso, fazer o json fake)
                        .content(userJSON)) // enviar
                .andExpect(status().isCreated());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/user/delete").param("login", user.getLogin()))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/searchID").param("id", user.getId().toString()))
                .andExpect(status().isNotFound());
    }

}
