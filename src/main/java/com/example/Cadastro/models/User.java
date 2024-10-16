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
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean active;

    /*
        @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();

    código usado anteriormente por mim, antes de conhecer a anotação @UUIDGenerator
     */
}