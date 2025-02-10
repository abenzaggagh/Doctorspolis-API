package com.doctorspolis.controller;

import com.doctorspolis.model.dto.doctor.DoctorDTO;
import com.doctorspolis.model.dto.search.SearchRequestDTO;
import com.doctorspolis.service.SearchService;
import com.doctorspolis.utility.PageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.doctorspolis.utility.DoctorspolisConstants.SEARCH_ENDPOINT;

@RequiredArgsConstructor

@RestController
@RequestMapping(SEARCH_ENDPOINT)
public class SearchController {

    private final SearchService searchService;

    @PostMapping("doctors")
    public ResponseEntity<PageDTO<DoctorDTO>> search(@RequestBody SearchRequestDTO request, Pageable pageable) {
        return ResponseEntity.ok(searchService.search(request, pageable));
    }

    @PostMapping("nearby-doctors")
    public ResponseEntity<PageDTO<DoctorDTO>> nearbyDoctors(@RequestBody SearchRequestDTO request) {
        return ResponseEntity.ok(searchService.nearbyDoctors(request));
    }

    @GetMapping("doctor")
    public ResponseEntity<DoctorDTO> searchDoctor(@RequestParam Long id) {
        return ResponseEntity.ok(this.searchService.searchDoctorByID(id));
    }

}
