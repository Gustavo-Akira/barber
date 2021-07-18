package br.gustavoakira.barber.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Date date;

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private Client client;

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private Barber barber;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Service> service;

    private Time startTime;

    private Time endTime;

    public Double getTotal(){
        return service.stream().mapToDouble(Service::getValue).sum();
    }
}
