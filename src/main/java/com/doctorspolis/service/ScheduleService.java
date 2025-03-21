package com.doctorspolis.service;

import com.doctorspolis.model.data.doctor.OpeningHours;
import com.doctorspolis.model.data.doctor.WorkSchedule;
import com.doctorspolis.model.data.schedule.Appointment;
import com.doctorspolis.model.dto.schedule.ScheduleRequestDTO;
import com.doctorspolis.model.dto.schedule.ScheduleDTO;
import com.doctorspolis.repository.AppointmentRepository;
import com.doctorspolis.repository.DoctorRepository;
import com.doctorspolis.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor

@Service
public class ScheduleService {

    public static final int DAYS = 7;

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    private final AppointmentRepository appointmentRepository;

    public List<ScheduleDTO> availableAppointments(Long doctorID) {
        List<ScheduleDTO> schedules = new ArrayList<>();

        doctorRepository.findById(doctorID).ifPresent(doctor -> {
            Integer appointmentDuration = doctor.getAppointmentDuration();

            WorkSchedule workSchedule = doctor.getWorkSchedule();

            for (int i = 0; i < DAYS; i++) {
                LocalDate day = LocalDate.now().plusDays(i);

                OpeningHours openingHours = workSchedule.getOpeningHours()
                        .stream()
                        .filter(openingHour -> openingHour.getDay().getValue().equals(day.getDayOfWeek().getValue()))
                        .findFirst()
                        .orElseThrow();

                getAvailableByDay(schedules, openingHours, appointmentDuration, day);

                setAvailability(doctorID, openingHours, day, schedules);

                // Disable the past schedules
                schedules
                        .stream()
                        .filter(schedule -> schedule.getStartDateTime().isBefore(LocalDateTime.now()))
                        .forEach(schedule -> schedule.setIsBooked(true));
            }

        });

        return schedules;
    }

    /**
     * Method that set the existing schedule to booked as true.
     * @param doctorID
     * @param openingHours schedule of the day
     * @param day the day
     * @param schedules list of all the available schedule
     */
    private void setAvailability(Long doctorID,
                                 OpeningHours openingHours,
                                 LocalDate day,
                                 List<ScheduleDTO> schedules) {
        if (openingHours.getOpens()) {
            var appointments = appointmentRepository.findByDoctorIDAndStartDateTimeBetween(
                    doctorID,
                    LocalDateTime.of(day, openingHours.getOpeningTime()),
                    LocalDateTime.of(day, openingHours.getClosingTime()));

            for(Appointment appointment : appointments) {
                schedules.stream()
                        .filter(s -> s.getStartDateTime().equals(appointment.getStartDateTime()))
                        .findFirst()
                        .ifPresent(s -> s.setIsBooked(true));
            }
        }
    }

    private void getAvailableByDay(final List<ScheduleDTO> schedules,
                                   final OpeningHours openingHours,
                                   final Integer appointmentDuration,
                                   final LocalDate day) {
        if (openingHours.getOpens()) {
            LocalTime currentTime = openingHours.getOpeningTime();

            if (openingHours.getHasBreak()) {
                getSchedule(schedules, appointmentDuration, day, currentTime, openingHours.getBreakTime());

                currentTime = openingHours.getResumeTime();

                getSchedule(schedules, appointmentDuration, day, currentTime, openingHours.getClosingTime());
            } else {
                getSchedule(schedules, appointmentDuration, day, currentTime, openingHours.getClosingTime());
            }
        }
    }

    private void getSchedule(final List<ScheduleDTO> schedules,
                                    final Integer appointmentDuration,
                                    final LocalDate day,
                                    LocalTime currentTime,
                                    final LocalTime closingTime) {
        while (currentTime.isBefore(closingTime)) {
            ScheduleDTO scheduleDTO = new ScheduleDTO();

            scheduleDTO.setStartDateTime(LocalDateTime.of(day, currentTime));
            scheduleDTO.setEndDateTime(LocalDateTime.of(day, currentTime.plusMinutes(appointmentDuration)));
            scheduleDTO.setIsBooked(false);

            schedules.add(scheduleDTO);

            currentTime = currentTime.plusMinutes(appointmentDuration);
        }
    }

    public Boolean bookAppointment(ScheduleRequestDTO scheduleAppointmentDTO) {
        Long doctorID = scheduleAppointmentDTO.getDoctorID();
        Long patientID = scheduleAppointmentDTO.getPatientID();
        LocalDateTime dueDateTime = scheduleAppointmentDTO.getDueDateTime();

        AtomicBoolean result = new AtomicBoolean();

        if (appointmentRepository.findByDoctorIDAndStartDateTime(doctorID, dueDateTime).isEmpty()) {
            doctorRepository.findById(doctorID).ifPresent(doctor -> {
                patientRepository.findById(patientID).ifPresent(patient -> {
                    Integer appointmentDuration = doctor.getAppointmentDuration();

                    Appointment appointment = new Appointment();
                    appointment.setDoctor(doctor);
                    appointment.setPatient(patient);
                    appointment.setStartDateTime(dueDateTime);
                    appointment.setEndDateTime(dueDateTime.plusMinutes(appointmentDuration));

                    appointmentRepository.save(appointment);

                    result.set(true);
                });
            });
        } else {
            throw new RuntimeException("Appointment already exists");
        }

        return result.get();
    }


}
