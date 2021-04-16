package br.gustavoakira.barber.repository;

import br.gustavoakira.barber.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberRepository extends JpaRepository<Barber,Long> {
}
