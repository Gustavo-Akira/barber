package br.gustavoakira.barber.controller;

import br.gustavoakira.barber.controller.annotations.BaseController;
import br.gustavoakira.barber.controller.utils.LoginUtils;
import br.gustavoakira.barber.exception.ForbiddenActionException;
import br.gustavoakira.barber.model.Appointment;
import br.gustavoakira.barber.service.AppointmentService;
import br.gustavoakira.barber.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@BaseController
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @Autowired
    private ClientService clientService;

    @Autowired
    private LoginUtils utils;

    @GetMapping("appointments")
    public ResponseEntity<List<Appointment>> getAppointments(){
        return ResponseEntity.ok(service.getAllAppointments(utils.getLoggedUser()));
    }

    @GetMapping("appointment/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id){
        Appointment appointment = service.getAppointment(id);
        if (!appointment.getBarber().equals(utils.getLoggedUser())){
            throw new ForbiddenActionException("This appointment is not yours");
        }
        return ResponseEntity.ok(appointment);
    }

    @PostMapping("appointment/client/{id}")
    public ResponseEntity<Appointment> saveAppointment(@PathVariable Long id,@RequestBody Appointment appointment){
        appointment.setBarber(utils.getLoggedUser());
        appointment.setClient(clientService.getClient(id));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAppointment(appointment));
    }

    @PutMapping("appointment/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment){
        if (!service.getAppointment(id).getBarber().equals(utils.getLoggedUser())){
            throw new ForbiddenActionException("This appointment is not yours");
        }
        return ResponseEntity.ok(service.updateAppointment(id,appointment));
    }

    @DeleteMapping("appointment/{id}")
    public ResponseEntity<Appointment> removeAppointment(@PathVariable Long id){
        if (!service.getAppointment(id).getBarber().equals(utils.getLoggedUser())){
            throw new ForbiddenActionException("This appointment is not yours");
        }
        return ResponseEntity.ok(service.removeAppointment(id));
    }
}
