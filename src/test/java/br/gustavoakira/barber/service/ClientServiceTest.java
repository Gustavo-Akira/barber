package br.gustavoakira.barber.service;

import br.gustavoakira.barber.model.Client;
import br.gustavoakira.barber.repository.ClientRepository;
import br.gustavoakira.barber.service.impl.ClientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({SpringExtension.class})
class ClientServiceTest {

    @Autowired
    private ClientService service;

    @MockBean
    private ClientRepository repository;

    private List<Client> clients = new ArrayList<>();

    private Client client = new Client();

    @TestConfiguration
    static class ClientServiceTestConfiguration{
        @Bean
        public ClientService clientService(){
            return new ClientServiceImpl();
        }
    }

    @BeforeEach
    void setUp() {
        client.setName("Akira");
        client.setId(1L);
        clients.add(client);
        Mockito.when(repository.findAll()).thenReturn(clients);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(client));
        Mockito.when(repository.save(client)).thenReturn(client);
    }

    @Test
    void getAllClients() {
        assertEquals(clients,service.getAllClients());
    }

    @Test
    void getClient() {
        assertEquals(client,service.getClient(1L));
    }

    @Test
    void createNewClient() {
        assertEquals(client, service.createNewClient(client));
    }

    @Test
    void updateClient() {
        assertEquals(client,service.updateClient(1L,client));
    }

    @Test
    void removeClient() {
        assertEquals(client, service.removeClient(1L));
    }
}