package com.doctorspolis.repository;

import com.doctorspolis.model.data.schedule.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorIDAndStartDateTimeBetween(Long doctorID,
                                                            LocalDateTime startDateTime,
                                                            LocalDateTime endDateTime);

    Optional<Appointment> findByDoctorIDAndStartDateTime(Long doctorID, LocalDateTime startDateTime);

}
