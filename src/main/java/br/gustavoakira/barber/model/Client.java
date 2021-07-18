package br.gustavoakira.barber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

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
    @JsonIgnore
    private List<Appointment> appointment = new ArrayList<>();

    private Double amount;

    public Double getAmount() {
        if (!appointment.isEmpty()) {
            appointment.stream().flatMapToDouble(x -> DoubleStream.of(x.getTotal())).reduce(0, (total, element) -> total + element);
        }
        return 0.0;
    }
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonIgnore
    private Barber barber;
}
