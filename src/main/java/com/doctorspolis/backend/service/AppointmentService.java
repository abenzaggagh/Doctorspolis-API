package com.doctorspolis.backend.service;

import com.doctorspolis.backend.exception.AppointmentConflictException;
import com.doctorspolis.backend.exception.ResourceNotFoundException;
import com.doctorspolis.backend.model.Appointment;
import com.doctorspolis.backend.model.DTO.AppointmentDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.model.Patient;
import com.doctorspolis.backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    DoctorService doctorService;

    PatientService patientService;

    AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(DoctorService doctorService,
                              PatientService patientService,
                              AppointmentRepository appointmentRepository) {
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.appointmentRepository = appointmentRepository;
    }

    public AppointmentDTO make(AppointmentDTO appointmentDTO) {
        Doctor doctor = doctorService.oneEntity(appointmentDTO.getDoctorID());

        Patient patient = patientService.oneEntity(appointmentDTO.getPatientID());

        if (!ObjectUtils.isEmpty(doctor) && !ObjectUtils.isEmpty(patient)) {
            List<Appointment> appointmentList = appointmentRepository.findAllByDoctorAndDueDateTime(doctor, appointmentDTO.getDueDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());

            Date startingAppointmentDate = appointmentDTO.getDueDateTime();
            Date endingAppointmentDate = new Date(appointmentDTO.getDueDateTime().getTime() + (appointmentDTO.getPeriodTime() * 60 * 1000));

            appointmentList.forEach(appointment -> {
                Date startingExistingAppointmentDate = Date.from(appointment.getDueDateTime().atZone(ZoneId.systemDefault()).toInstant());
                Date endingExistingAppointmentDate = Date.from(appointment.getDueDateTime().atZone(ZoneId.systemDefault()).toInstant());

                if ((startingAppointmentDate.before(endingExistingAppointmentDate)
                        && startingExistingAppointmentDate.before(endingAppointmentDate))
                    || startingAppointmentDate.equals(startingExistingAppointmentDate)) {
                    throw new AppointmentConflictException("error.appointment.conflict", "error.appointment.conflict.details", startingAppointmentDate, endingAppointmentDate);
                }
            });

            Appointment appointment = Appointment.builder()
                    .doctor(doctor)
                    .patient(patient)
                    .description(appointmentDTO.getDescription())
                    .periodTime(appointmentDTO.getPeriodTime())
                    .dueDateTime(appointmentDTO.getDueDateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()).build();

            appointmentRepository.save(appointment);
        }


        return appointmentDTO;
    }

    public Boolean cancel(Long appointmentID) {
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(appointmentID);

        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();

            LocalDate today = LocalDate.now();
            LocalDate cancellationLimitDate = LocalDate.now().plusDays(2);
            LocalDate appointmentDateTime = appointment.getDueDateTime().toLocalDate();

            if (appointmentDateTime.isBefore(today) || appointmentDateTime.isEqual(today)) {
                // throw new AppointmentCancellationProhibitedException(appointmentID.toString());
                throw new AppointmentConflictException("error.appointment.cancellation.prohibited", "error.appointment.cancellation.prohibited.details", "");
            } else if (appointmentDateTime.isBefore(cancellationLimitDate)) {
                throw new AppointmentConflictException("error.appointment.cancellation.prohibited", "error.appointment.cancellation.prohibited.details", "");
            }

            appointment.setCanceled(true);

            appointmentRepository.save(appointment);

            return true;
        } else {
            throw new ResourceNotFoundException(appointmentID.toString());
        }
    }

}
