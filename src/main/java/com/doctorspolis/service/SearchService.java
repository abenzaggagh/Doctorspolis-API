package com.doctorspolis.service;

import com.doctorspolis.model.data.doctor.Doctor;
import com.doctorspolis.model.dto.doctor.DoctorDTO;
import com.doctorspolis.model.dto.search.SearchRequestDTO;
import com.doctorspolis.repository.DoctorRepository;
import com.doctorspolis.utility.DoctorspolisConstants;
import com.doctorspolis.utility.DoctorspolisDecorator;
import com.doctorspolis.utility.PageDTO;
import com.doctorspolis.utility.mapper.DoctorMapper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;

@RequiredArgsConstructor

// TODO: Implement Error Handling
@Service
public class SearchService {

    private final DoctorRepository doctorRepository;

    private final DoctorMapper doctorMapper;


    public PageDTO<DoctorDTO> search(SearchRequestDTO request, Pageable pageable) {
        PageDTO<DoctorDTO> resultPage;

        if (!ObjectUtils.isEmpty(request.getSpeciality())) {
            resultPage = getPageDTOByQueryAndSpeciality(request, pageable);
        } else {
            resultPage = getPageDTOByQuery(request, pageable);
        }

        resultPage.setPageSize(resultPage.getResult().size());

        return calculateDistance(request, resultPage);
    }

    private PageDTO<DoctorDTO> getPageDTOByQuery(SearchRequestDTO request, Pageable pageable) {
        PageDTO<DoctorDTO> resultPage = new PageDTO<>();

        Arrays.stream(request.getQuery().split(" ")).forEach(queryString -> {
            Page<Doctor> result = doctorRepository.findByLocation(
                    queryString,
                    request.getLocation().getLatitude(),
                    request.getLocation().getLongitude(),
                    request.getLocation().getRadius(),
                    pageable);

            resultPage.getResult().addAll(doctorMapper.toDTOs(result.getContent()));
            resultPage.setPageNumber(result.getNumber());
            resultPage.setHasNextPage(resultPage.getHasNextPage() || result.hasNext());
        });

        return resultPage;
    }

    private PageDTO<DoctorDTO> getPageDTOByQueryAndSpeciality(SearchRequestDTO request, Pageable pageable) {
        PageDTO<DoctorDTO> resultPage = new PageDTO<>();

        Arrays.stream(request.getQuery().split(" ")).forEach(queryString -> {
            Page<Doctor> result = doctorRepository.findByLocationAndSpeciality(
                    queryString,
                    request.getSpeciality(),
                    request.getLocation().getLatitude(),
                    request.getLocation().getLongitude(),
                    request.getLocation().getRadius(),
                    pageable);

            resultPage.getResult().addAll(doctorMapper.toDTOs(result.getContent()));
            resultPage.setPageNumber(result.getNumber());
            resultPage.setHasNextPage(resultPage.getHasNextPage() || result.hasNext());
        });

        return resultPage;
    }


    public PageDTO<DoctorDTO> nearbyDoctors(SearchRequestDTO request) {
        var queryStrings = Arrays.stream(request.getQuery().split(DoctorspolisConstants.SPACE));

        PageDTO<DoctorDTO> resultPage = new PageDTO<>();

        queryStrings.forEach(queryString -> {
            Page<Doctor> result;
            result = doctorRepository.findByLocation(
                    DoctorspolisConstants.EMPTY_QUERY,
                    request.getLocation().getLatitude(),
                    request.getLocation().getLongitude(),
                    DoctorspolisConstants.NEARBY_RADIUS,
                    PageRequest.of(0, 10));


            resultPage.getResult().addAll(doctorMapper.toDTOs(result.getContent()));

            resultPage.setPageNumber(result.getNumber());
        });

        return calculateDistance(request, resultPage);
    }


    public DoctorDTO searchDoctorByID(Long id) {
        return doctorMapper.toDTO(doctorRepository.findById(id).orElseThrow(RuntimeException::new));
    }


    private PageDTO<DoctorDTO> calculateDistance(final SearchRequestDTO request, PageDTO<DoctorDTO> resultPage) {
        resultPage.getResult().forEach(doctorDTO -> {
            val distance = DoctorspolisDecorator.calculateDistance(
                    doctorDTO.getWorkPlace().getLatitude(),
                    doctorDTO.getWorkPlace().getLongitude(),
                    request.getLocation().getLatitude(),
                    request.getLocation().getLongitude());

            doctorDTO.getWorkPlace().setDistance(distance);
        });

        return resultPage;
    }

}
