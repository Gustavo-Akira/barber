package br.gustavoakira.barber.service;

import br.gustavoakira.barber.model.Appointment;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAllAppointments();
    Appointment getAppointment(Long id);
    Appointment updateAppointment(Long id,Appointment appointment);
    Appointment removeAppointment(Long id);
}
