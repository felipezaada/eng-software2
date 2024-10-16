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
@Table(name = "tb_city")
public class City {
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @Id
    private UUID id;
    @Column(nullable = false)
    private String name;
    @ManyToOne // um estado tem muitas cidades, muitas cidades tem um estado
    @JoinColumn(name = "state_id", nullable = false) // tive que procurar isso, não sabia como fazer
    private State state;
}
