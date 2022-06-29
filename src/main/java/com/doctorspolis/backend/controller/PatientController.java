package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.model.DTO.PatientDTO;
import com.doctorspolis.backend.model.DTO.PrescriptionDTO;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.service.PrescriptionService;
import com.doctorspolis.backend.utility.CRUDController;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(DoctorspolisConstants.PATIENTS)
public class PatientController implements CRUDController<PatientDTO> {

    private final PrescriptionService prescriptionService;

    @Autowired
    public PatientController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Collection<PatientDTO>> all() {
        return CRUDController.super.all();
    }

    @Override
    @GetMapping(DoctorspolisConstants.PATIENT_ID_PATH_VARIABLE)
    public ResponseEntity<PatientDTO> one(@PathVariable Long patientID) {
        return CRUDController.super.one(patientID);
    }

    @Override
    public ResponseEntity<PatientDTO> create(PatientDTO patientDTO) {
        return CRUDController.super.create(patientDTO);
    }

    @Override
    public ResponseEntity<PatientDTO> update(Long ID, PatientDTO entity) {
        return CRUDController.super.update(ID, entity);
    }

    @Override
    public ResponseEntity<Boolean> delete(Long ID) {
        return CRUDController.super.delete(ID);
    }

    @RestController
    @RequestMapping(DoctorspolisConstants.PATIENTS_PRESCRIPTIONS)
    class PrescriptionController {

        @GetMapping
        public ResponseEntity<Collection<PrescriptionDTO>> all(@AuthenticationPrincipal User user,
                                                               @PathVariable Long patientID) {
            return ResponseEntity.ok(prescriptionService.allByPatientID(user, patientID));
        }

    }

}
