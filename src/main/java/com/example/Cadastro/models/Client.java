package com.example.Cadastro.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_client")
public class Client {
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @ManyToOne // um cliente tem uma cidade, uma cidade tem vários clientes
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
    @Column(nullable = false)
    private boolean active;
    @Column(nullable = false)
    private LocalDateTime birthday;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // um cliente tem muitos filhos
    @JoinColumn(name = "client_id", nullable = false) // referência (nome da chave estrangeira)
    private List<Child> children;
}
