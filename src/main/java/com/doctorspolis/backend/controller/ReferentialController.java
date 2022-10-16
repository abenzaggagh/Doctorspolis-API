package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.model.User;
import com.doctorspolis.backend.model.referential.DTO.CountryDTO;
import com.doctorspolis.backend.model.referential.DTO.LanguageDTO;
import com.doctorspolis.backend.model.referential.DTO.MedicationDTO;
import com.doctorspolis.backend.model.referential.DTO.SpecialityDTO;
import com.doctorspolis.backend.model.referential.Medication;
import com.doctorspolis.backend.service.ReferentialService;
import com.doctorspolis.backend.utility.commun.AbstractController;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(DoctorspolisConstants.REFERENTIAL)
public class ReferentialController extends AbstractController {

    private final ReferentialService referentialService;

    @Autowired
    public ReferentialController(ReferentialService referentialService) {
        this.referentialService = referentialService;
    }

    @GetMapping(DoctorspolisConstants.COUNTRIES)
    public ResponseEntity<Collection<CountryDTO>> getCountries() {
        return ResponseEntity.ok().body(this.referentialService.getCountries());
    }

    @GetMapping(DoctorspolisConstants.COUNTRY_BY_CODE)
    public ResponseEntity<CountryDTO> getCountryByCode(@PathVariable String code) {
        return ResponseEntity.ok().body(this.referentialService.getCountryByCode(code));
    }

    @GetMapping(DoctorspolisConstants.LANGUAGES)
    public ResponseEntity<Collection<LanguageDTO>> getLanguages() {
        return ResponseEntity.ok().body(this.referentialService.getLanguages());
    }

    @GetMapping(DoctorspolisConstants.LANGUAGE_BY_CODE)
    public ResponseEntity<LanguageDTO> getLanguageByCode(@PathVariable String code) {
        return ResponseEntity.ok().body(this.referentialService.getLanguageByCode(code));
    }

    @GetMapping(DoctorspolisConstants.SPECIALITIES)
    public ResponseEntity<Collection<SpecialityDTO>> getSpecialities() {
        return ResponseEntity.ok().body(this.referentialService.getSpecialities());
    }

    @GetMapping(DoctorspolisConstants.SPECIALITY_BY_CODE)
    public ResponseEntity<SpecialityDTO> getSpecialityByCode(@PathVariable String code) {
        return ResponseEntity.ok().body(this.referentialService.getSpecialityByCode(code));
    }

    @GetMapping(DoctorspolisConstants.MEDICATIONS)
    public ResponseEntity<Collection<MedicationDTO>> getMedications() {
        return ResponseEntity.ok().body(this.referentialService.getMedications());
    }

    @GetMapping(DoctorspolisConstants.MEDICATIONS_BY_CODE)
    public ResponseEntity<MedicationDTO> getMedicationByCode(@PathVariable String code) {
        return ResponseEntity.ok().body(this.referentialService.getMedicationByCode(code));
    }

    @PostMapping(DoctorspolisConstants.MEDICATIONS)
    public ResponseEntity<MedicationDTO> addMedication(@AuthenticationPrincipal User user, @RequestBody MedicationDTO medicationDTO) {
        return ResponseEntity.ok().body(this.referentialService.createMedication(user, medicationDTO));
    }

}
