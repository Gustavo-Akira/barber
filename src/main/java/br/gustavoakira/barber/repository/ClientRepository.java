package br.gustavoakira.barber.repository;

import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    @Query("SELECT c FROM Client c WHERE c.barber = ?1 ORDER BY id")
    Page<Client> getClientsByUser(Barber barber, Pageable pageable);
}
