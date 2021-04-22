package br.gustavoakira.barber.repository;

import br.gustavoakira.barber.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BarberRepository extends JpaRepository<Barber,Long> {
    @Query("SELECT b from Barber b where b.username = ?1")
    Optional<Barber> getBarberByUsername(String username);
}
