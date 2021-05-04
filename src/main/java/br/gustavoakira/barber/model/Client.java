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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "client")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Appointment> appointment;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Barber> barber;
}
