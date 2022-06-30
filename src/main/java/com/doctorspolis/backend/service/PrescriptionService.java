package com.doctorspolis.backend.service;

import com.doctorspolis.backend.controller.exception.ResourceNotFoundException;
import com.doctorspolis.backend.model.DTO.PrescriptionDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.model.Patient;
import com.doctorspolis.backend.model.Prescription;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.model.referential.Medication;
import com.doctorspolis.backend.model.repository.DoctorRepository;
import com.doctorspolis.backend.model.repository.MedicationRepository;
import com.doctorspolis.backend.model.repository.PatientRepository;
import com.doctorspolis.backend.model.repository.PrescriptionRepository;
import com.doctorspolis.backend.utility.mapper.PrescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collection;

@Service
public class PrescriptionService {

    private final DoctorRepository doctorRepository;

    private final PatientRepository patientRepository;

    private final PrescriptionMapper prescriptionMapper;

    private final MedicationRepository medicationRepository;
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    public PrescriptionService(DoctorRepository doctorRepository,
                               PatientRepository patientRepository,
                               PrescriptionMapper prescriptionMapper,
                               MedicationRepository medicationRepository,
                               PrescriptionRepository prescriptionRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.prescriptionMapper = prescriptionMapper;
        this.medicationRepository = medicationRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    public ResponseEntity<Collection<PrescriptionDTO>> all() {
        Patient patient = patientRepository.getById(1L);
        return ResponseEntity.ok(prescriptionMapper.toDTOs(prescriptionRepository.findAllByPatient(patient)));
    }

    public PrescriptionDTO create(User user,
                                  Long doctorID,
                                  PrescriptionDTO prescriptionDTO) throws ResourceNotFoundException {
        if (!ObjectUtils.isEmpty(user) && user.isDoctor()) {

            Doctor doctor = doctorRepository.findById(doctorID).orElseThrow(() -> new ResourceNotFoundException(""));

            if (ObjectUtils.isEmpty(prescriptionDTO)
                    && ObjectUtils.isEmpty(prescriptionDTO.getPatient())
                    && ObjectUtils.isEmpty(prescriptionDTO.getPatient().getID())) {
                throw new ResourceNotFoundException("");
            }

            Patient patient = patientRepository.findById(prescriptionDTO.getPatient().getID()).orElseThrow(() -> new ResourceNotFoundException(""));

            Prescription prescription = prescriptionMapper.toEntity(prescriptionDTO);
            prescription.setDoctor(doctor);
            prescription.setPatient(patient);
            /*
            for(IngredientDTO ingredientDTO: prescriptionDTO.getIngredients()) {
                if (!ObjectUtils.isEmpty(ingredientDTO)
                        && !ObjectUtils.isEmpty(ingredientDTO.getMedication())
                        && !ObjectUtils.isEmpty(ingredientDTO.getMedication().getCode())) {
                    Medication medication = medicationRepository.findMedicationByCode(ingredientDTO.getMedication().getCode()).orElseThrow(() -> new ResourceNotFoundException("ffff"));

                }
            } */

            prescription.getIngredients().forEach(ingredient -> {
                /*
                if (!ObjectUtils.isEmpty(ingredientDTO)
                && !ObjectUtils.isEmpty(ingredientDTO.getMedication())
                && !ObjectUtils.isEmpty(ingredientDTO.getMedication().getCode())) {*/
                Medication medication = medicationRepository.findMedicationByCode(ingredient.getMedication().getCode()).orElseThrow(() -> new ResourceNotFoundException("ffff"));
                ingredient.setMedication(medication);
                // }

            });

            return prescriptionMapper.toDTO(prescriptionRepository.save(prescription));
        }

        // TODO: Change th e Exception for an Forbidden resource
        throw new ResourceNotFoundException("dddd");
    }

    public Collection<PrescriptionDTO> allByPatientID(User user, Long patientID) throws ResourceNotFoundException {
        /* if ((!ObjectUtils.isEmpty(user) && !user.getID().equals(patientID) || !user.isAdmin())) {
            // TODO: Change th e Exception for an Forbidden resource
            throw new ResourceNotFoundException("dddd");
        } */
        Patient patient = patientRepository.getById(patientID);
        return prescriptionMapper.toDTOs(prescriptionRepository.findAllByPatient(patient));
    }

    public Collection<PrescriptionDTO> allByDoctorID(User user, Long doctorID) throws ResourceNotFoundException {
        if ((!ObjectUtils.isEmpty(user) && !user.getID().equals(doctorID) || user.isAdmin())) {
            // TODO: Change th e Exception for an Forbidden resource
            throw new ResourceNotFoundException("dddd");
        }
        Doctor doctor = doctorRepository.getById(doctorID);
        return prescriptionMapper.toDTOs(prescriptionRepository.findAllByDoctor(doctor));
    }


}
