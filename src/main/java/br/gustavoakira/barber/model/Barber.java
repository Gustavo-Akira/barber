package br.gustavoakira.barber.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String color;

    @OneToMany(mappedBy = "barber")
    private List<Appointment> appointments;

    @ManyToMany
    private List<Service> service;

    @OneToMany
    private List<Client> clients;

    private String username;

    private String password;
}
