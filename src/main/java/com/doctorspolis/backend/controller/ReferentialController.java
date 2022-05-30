package com.doctorspolis.backend.controller;

import com.doctorspolis.backend.commun.AbstractController;
import com.doctorspolis.backend.model.referential.DTO.CountryDTO;
import com.doctorspolis.backend.model.referential.DTO.LanguageDTO;
import com.doctorspolis.backend.service.ReferentialService;
import com.doctorspolis.backend.utility.constants.DoctorspolisConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(DoctorspolisConstants.REFERENTIAL)
public class ReferentialController extends AbstractController {

    private final ReferentialService referentialService;

    @Autowired
    public ReferentialController(ReferentialService referentialService) {
        this.referentialService = referentialService;
    }

    @GetMapping(DoctorspolisConstants.COUNTRIES)
    public ResponseEntity<List<CountryDTO>> getCountries() {
        return ResponseEntity.ok().body(this.referentialService.getCountries());
    }

    @GetMapping(DoctorspolisConstants.COUNTRY_BY_CODE)
    public ResponseEntity<CountryDTO> getCountryByCode(@PathVariable String code) {
        return ResponseEntity.ok().body(this.referentialService.getCountryByCode(code));
    }

    @GetMapping(DoctorspolisConstants.LANGUAGES)
    public ResponseEntity<List<LanguageDTO>> getLanguages() {
        return ResponseEntity.ok().body(this.referentialService.getLanguages());
    }

    @GetMapping(DoctorspolisConstants.LANGUAGE_BY_CODE)
    public ResponseEntity<LanguageDTO> getLanguageByCode(@PathVariable String code) {
        return ResponseEntity.ok().body(this.referentialService.getLanguageByCode(code));
    }

}
