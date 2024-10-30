package com.example.Cadastro.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O campo nome não pode estar vazio.")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres.")
    private String name;
    @NotBlank(message = "O campo login não pode estar vazio.")
    @Size(min = 3, message = "O login deve ter no máximo 15 caracteres.")
    private String login;
    @NotBlank(message = "O campo senha não pode estar vazia.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String password;
    private boolean active;

}