package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.controller.exception.DoctorNotFoundException;
import com.doctorspolis.backend.model.DTO.*;
import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.service.DoctorService;
import com.doctorspolis.backend.service.PrescriptionService;
import com.doctorspolis.backend.utility.CRUDController;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import com.doctorspolis.backend.utility.wrapper.WorkScheduleWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(DoctorspolisConstants.DOCTORS)
public class DoctorController implements CRUDController<DoctorDTO> {

    private final DoctorService doctorService;

    private final PrescriptionService prescriptionService;

    @Autowired
    public DoctorController(DoctorService doctorService, PrescriptionService prescriptionService) {
        this.doctorService = doctorService;
        this.prescriptionService = prescriptionService;
    }

    @Override
    @GetMapping
    public ResponseEntity<Collection<DoctorDTO>> all() {
        return ResponseEntity.ok(doctorService.allDTOs());
    }

    @Override
    @GetMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public ResponseEntity<DoctorDTO> one(@PathVariable Long doctorID) throws DoctorNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.oneDTO(doctorID));
    }

    @Override
    @PostMapping
    public ResponseEntity<DoctorDTO> create(@RequestBody DoctorDTO doctor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.create(doctor));
    }

    @Override
    @PutMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public ResponseEntity<DoctorDTO> update(@PathVariable Long doctorID,
                                            @RequestBody DoctorDTO doctorDTO) throws DoctorNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.replaceDoctor(doctorID, doctorDTO));
    }

    @Override
    @PatchMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public ResponseEntity<DoctorDTO> edit(@PathVariable Long doctorID,
                                          @RequestBody DoctorDTO doctorDTO) throws DoctorNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.updateByID(doctorID, doctorDTO));
    }

    @Override
    @DeleteMapping(DoctorspolisConstants.DOCTOR_ID_PATH_VARIABLE)
    public ResponseEntity<Boolean> delete(@PathVariable Long doctorID) throws DoctorNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(doctorService.deleteByID(doctorID));
    }


    @RestController
    @RequestMapping(DoctorspolisConstants.DOCTORS_WORK_SCHEDULE)
    class WorkSchedule {

        @GetMapping
        public ResponseEntity<Collection<WorkScheduleDTO>> all(@PathVariable Long doctorID) {
            return ResponseEntity.status(HttpStatus.OK).body(doctorService.getWorkScheduleDTOSByDoctorID(doctorID));
        }

        @PostMapping
        public ResponseEntity<Collection<WorkScheduleDTO>> create(@PathVariable Long doctorID,
                                                               @RequestBody WorkScheduleDTO workScheduleDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.addWorkScheduleByID(doctorID, workScheduleDTO));
        }

        @PutMapping
        public ResponseEntity<Collection<WorkScheduleDTO>> update(@PathVariable Long doctorID,
                                                                   @RequestBody WorkScheduleWrapper workSchedules) {
            return ResponseEntity.status(HttpStatus.OK).body(doctorService.replaceWorkScheduleByID(doctorID, workSchedules.getWorkSchedule()));
        }

        @PatchMapping("{workScheduleID}")
        public ResponseEntity<WorkScheduleDTO> edit(@PathVariable Long doctorID,
                                                    @PathVariable Long workScheduleID,
                                                    @RequestBody WorkScheduleDTO workScheduleDTO) {
            return ResponseEntity.status(HttpStatus.OK).body(doctorService.editWorkScheduleByID(doctorID, workScheduleID, workScheduleDTO));
        }

        @DeleteMapping("{workScheduleID}")
        public ResponseEntity<Collection<WorkScheduleDTO>> delete(@PathVariable Long doctorID,
                                                                  @PathVariable Long workScheduleID) throws DoctorNotFoundException {
            return ResponseEntity.status(HttpStatus.OK).body(doctorService.deleteWorkScheduleByID(doctorID, workScheduleID));
        }

    }


    @RestController
    @RequestMapping(DoctorspolisConstants.DOCTORS_PRESCRIPTIONS)
    class Prescriptions {

        @PostMapping
        public ResponseEntity<PrescriptionDTO> create(@AuthenticationPrincipal User user,
                                                      @PathVariable Long doctorID,
                                                      @RequestBody PrescriptionDTO prescriptionDTO) {
            // TODO Change the created response with URI
            //  ResponseEntity.created(prescriptionService.create(user, doctorID, prescriptionDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(prescriptionService.create(user, doctorID, prescriptionDTO));
        }

    }

    /* Keep the Search methods for later. */
    // TODO: Implement the search controller for doctors
    //       Change the GET method to POST
    //       Return 206 PARTIAL CONTENT if totalElement > totalContent
    @PostMapping(DoctorspolisConstants.SEARCH)
    public ResponseEntity<PageDTO<DoctorDTO>> search(@RequestBody SearchRequest request, Pageable pageable) {
        PageDTO<DoctorDTO> searchResult = doctorService.searchDoctors(request, pageable);
        return ResponseEntity.status(searchResult.getTotalPages() > 0 ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK).body(searchResult);
    }

}
