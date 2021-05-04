package br.gustavoakira.barber.service.impl;

import br.gustavoakira.barber.exception.ResourceNotFoundException;
import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.model.Client;
import br.gustavoakira.barber.repository.ClientRepository;
import br.gustavoakira.barber.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public List<Client> getAllClients(Barber barber) {
        List<Client> clients = repository.findAll();
        if(!clients.isEmpty()) {
            return clients.stream().filter(x -> x.getBarber() == barber).collect(Collectors.toList());
        }else{
            return null;
        }
    }

    @Override
    public Client getClient(Long id) {
        return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Client not found with id = "+id));
    }

    @Override
    public Client createNewClient(Client client) {
        return repository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client old = getClient(id);
        client.setId(old.getId());
        return repository.save(client);
    }

    @Override
    public Client removeClient(Long id) {
        Client old = getClient(id);
        repository.delete(old);
        return old;
    }
}
