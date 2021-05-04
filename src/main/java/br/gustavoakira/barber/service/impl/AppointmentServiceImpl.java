package br.gustavoakira.barber.service.impl;

import br.gustavoakira.barber.exception.ResourceNotFoundException;
import br.gustavoakira.barber.model.Appointment;
import br.gustavoakira.barber.model.Barber;
import br.gustavoakira.barber.repository.AppointmentRepository;
import br.gustavoakira.barber.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    @Override
    public List<Appointment> getAllAppointments(Barber barber) {
        List<Appointment> appointments = repository.findAll();
        if(!appointments.isEmpty()) {
            return appointments.stream().filter(x -> x.getBarber().equals(barber)).collect(Collectors.toList());
        }else{
            return null;
        }
    }

    @Override
    public Appointment getAppointment(Long id) {
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Appointment not found with id = "+ id));
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

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return repository.save(appointment);
    }
}
