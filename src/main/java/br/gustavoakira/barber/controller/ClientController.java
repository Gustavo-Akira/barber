package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.controller.annotations.BaseController;
import br.gustavoakira.barber.controller.utils.LoginUtils;
import br.gustavoakira.barber.exception.ForbiddenActionException;
import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.model.Client;
import br.gustavoakira.barber.service.BarberService;
import br.gustavoakira.barber.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseController
public class ClientController {

    @Autowired
    private ClientService service;

    @Autowired
    private LoginUtils utils;

    @GetMapping("clients/{page}")
    public ResponseEntity<Page<Client>> getClients(@PathVariable("page") int page){
        Pageable pageable = PageRequest.of(page,5);
        return ResponseEntity.ok(service.getAllClients(utils.getLoggedUser(),pageable));
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id){
        Client client = service.getClient(id);
        if(!client.getBarber().equals(utils.getLoggedUser())){
            throw new ForbiddenActionException("This client is not yours");
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping("client")
    public ResponseEntity<Client> saveClient(@RequestBody Client client){
        client.setBarber(utils.getLoggedUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewClient(client));
    }

    @PutMapping("client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client){
        if(client.getBarber() != null) {
            if (!client.getBarber().equals(utils.getLoggedUser())) {
                throw new ForbiddenActionException("This client is not yours");
            }
        }
        client.setBarber(utils.getLoggedUser());
        return ResponseEntity.ok(service.updateClient(id, client));
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<Client> removeClient(@PathVariable Long id){
        if(!service.getClient(id).getBarber().equals(utils.getLoggedUser())){
            throw new ForbiddenActionException("This client is not yours");
        }
        return ResponseEntity.ok(service.removeClient(id));
    }
}
