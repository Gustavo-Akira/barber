package br.gustavoakira.barber.service.impl;

import br.gustavoakira.barber.model.Appointment;
import br.gustavoakira.barber.repository.AppointmentRepository;
import br.gustavoakira.barber.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public List<Appointment> getAllAppointments() {
        return repository.findAll();
    }

    @Override
    public Appointment getAppointment(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment appointment) {
        Appointment old = getAppointment(id);
        appointment.setId(old.getId());
        return repository.save(appointment);
    }

    @Override
    public Appointment removeAppointment(Long id) {
        Appointment old = getAppointment(id);
        repository.delete(old);
        return old;
    }
}