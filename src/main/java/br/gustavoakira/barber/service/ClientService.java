package br.gustavoakira.barber.service;

import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    Page<Client> getAllClients(Barber barber, Pageable pageable);
    Client getClient(Long id);
    Client createNewClient(Client client);
    Client updateClient(Long id,Client client);
    Client removeClient(Long id);
}
