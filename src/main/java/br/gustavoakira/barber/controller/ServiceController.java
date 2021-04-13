package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.controller.annotations.BaseController;
import br.gustavoakira.barber.model.Service;
import br.gustavoakira.barber.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseController
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("services")
    public ResponseEntity<List<Service>> getService() {
        return ResponseEntity.ok(serviceService.getAllService());
    }

    @GetMapping("service/{id}")
    public ResponseEntity<Service> getService(@PathVariable Long id){
        return  ResponseEntity.ok(serviceService.getService(id));
    }

    @PostMapping("service")
    public ResponseEntity<Service> saveService(@RequestBody Service service){
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceService.createNewService(service));
    }

    @PutMapping("service/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service service){
        return ResponseEntity.ok(serviceService.updateService(id,service));
    }

    @DeleteMapping("service/{id}")
    public ResponseEntity<Service> removeService(@PathVariable Long id){
        return ResponseEntity.ok(serviceService.deleteService(id));
    }
}
