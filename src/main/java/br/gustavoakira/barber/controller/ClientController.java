package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.controller.annotations.BaseController;
import br.gustavoakira.barber.model.Client;
import br.gustavoakira.barber.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseController
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping("clients")
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok(service.getAllClients());
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id){
        return ResponseEntity.ok(service.getClient(id));
    }

    @PostMapping("client")
    public ResponseEntity<Client> saveClient(@RequestBody Client client){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createNewClient(client));
    }

    @PutMapping("client/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client){
        return ResponseEntity.ok(service.updateClient(id, client));
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<Client> removeClient(@PathVariable Long id){
        return ResponseEntity.ok(service.removeClient(id));
    }
}
