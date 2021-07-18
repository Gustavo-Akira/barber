package br.gustavoakira.barber.repository;

import br.gustavoakira.barber.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query("SELECT a FROM Appointment a where a.date<=?1 or a.date>=?2")
    List<Appointment> getAppointmentByDate(Date date, Date maxDate);
}
