package ru.geekbrains.spring.thymeleaf.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "client_role_tbl")
@Data
@NoArgsConstructor
public class ClientRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_role_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public ClientRole(Client client, Role role){
        this.client = client;
        this.role = role;
    }
}
