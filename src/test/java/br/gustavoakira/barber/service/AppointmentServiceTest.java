package br.gustavoakira.barber.service;

import br.gustavoakira.barber.model.Appointment;
import br.gustavoakira.barber.repository.AppointmentRepository;
import br.gustavoakira.barber.service.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith({SpringExtension.class})
class AppointmentServiceTest {
    private LocalDateTime dateTime = LocalDateTime.now();
    private Appointment appointment = new Appointment();
    private List<Appointment> appointments = new ArrayList<Appointment>();
    @BeforeEach
    public void setUp(){
        appointment.setId(1L);
        appointment.setDate(dateTime);
        appointments.add(appointment);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(appointment));
        Mockito.when(repository.findAll()).thenReturn(appointments);
        Mockito.when(repository.save(appointment)).thenReturn(appointment);
    }
    @TestConfiguration
    static class AppointmentServiceImplConfiguration{
        @Bean
        public AppointmentService appointmentService(){
            return new AppointmentServiceImpl();
        }
    }

    @Autowired
    private AppointmentService service;

    @MockBean
    private AppointmentRepository repository;


    @Test
    void getAllAppointments() {
        assertEquals(appointments,service.getAllAppointments(appointment.getBarber()));
    }

    @Test
    void getAppointment() {
        assertEquals(dateTime,service.getAppointment(1L).getDate());
    }

    @Test
    void updateAppointment() {
        assertEquals(appointment,service.updateAppointment(1L,appointment));
    }

    @Test
    void removeAppointment() {
        assertEquals(appointment,service.removeAppointment(1L));
    }

    @Test
    void saveAppointment() {
       assertEquals(appointment,service.saveAppointment(appointment));
    }
}