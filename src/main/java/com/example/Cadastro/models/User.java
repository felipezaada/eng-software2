package com.example.Cadastro.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_users")
public class User {

    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Id
    private UUID id;
    private String name;
    private String login;
    private String password;
    private boolean active;

}