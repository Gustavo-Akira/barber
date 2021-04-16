package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.controller.annotations.BaseController;
import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseController
public class BarberController {
    @Autowired
    private BarberService service;

    @GetMapping("barbers")
    public ResponseEntity<List<Barber>> getBarbers(){
        return ResponseEntity.ok(service.getBarbers());
    }

    @GetMapping("barber/{id}")
    public ResponseEntity<Barber> getBarber(@PathVariable Long id){
        return ResponseEntity.ok(service.getBarber(id));
    }

    @PostMapping("barber")
    public ResponseEntity<Barber> createBarber(@RequestBody Barber barber){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveBarber(barber));
    }

    @PutMapping("barber/{id}")
    public ResponseEntity<Barber> updateBarber(@PathVariable Long id, @RequestBody Barber barber){
        return ResponseEntity.ok(service.updateBarber(id,barber));
    }

    @DeleteMapping("barber/{id}")
    public ResponseEntity<Barber> deleteBarber(@PathVariable Long id){
        return ResponseEntity.ok(service.removeBarber(id));
    }
}
