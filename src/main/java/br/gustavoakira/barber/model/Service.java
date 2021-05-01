package br.gustavoakira.barber.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private Double value;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Appointment> appointment;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Barber> barbers;
}
