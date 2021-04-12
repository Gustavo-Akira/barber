package br.gustavoakira.barber.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime date;

    @ManyToOne
    private Client client;

    @ManyToMany
    private List<Service> service;

    public Double getTotal(){
        return service.stream().mapToDouble(Service::getValue).sum();
    }
}
