package br.gustavoakira.barber.service;

import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    List<Client> getAllClients(Barber barber);
    Client getClient(Long id);
    Client createNewClient(Client client);
    Client updateClient(Long id,Client client);
    Client removeClient(Long id);
}
