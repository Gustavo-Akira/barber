package br.gustavoakira.barber.service;

import br.gustavoakira.barber.model.Appointment;
import br.gustavoakira.barber.model.Barber;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments(Barber barber);
    Appointment getAppointment(Long id);
    Appointment updateAppointment(Long id,Appointment appointment);
    Appointment removeAppointment(Long id);
    Appointment saveAppointment(Appointment appointment);
}
