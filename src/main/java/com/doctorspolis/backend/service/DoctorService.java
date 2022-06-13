package com.doctorspolis.backend.service;

import com.doctorspolis.backend.commun.AbstractService;
import com.doctorspolis.backend.exception.DoctorNotFoundException;
import com.doctorspolis.backend.exception.ResourceNotFoundException;
import com.doctorspolis.backend.helper.DoctorHelper;
import com.doctorspolis.backend.helper.mapper.DoctorMapper;
import com.doctorspolis.backend.helper.mapper.WorkScheduleMapper;
import com.doctorspolis.backend.model.DTO.DoctorDTO;
import com.doctorspolis.backend.model.DTO.PageDTO;
import com.doctorspolis.backend.model.DTO.SearchRequest;
import com.doctorspolis.backend.model.DTO.WorkScheduleDTO;
import com.doctorspolis.backend.model.Doctor;
import com.doctorspolis.backend.model.WorkSchedule;
import com.doctorspolis.backend.repository.DoctorRepository;
import com.doctorspolis.backend.repository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class DoctorService extends AbstractService {

    private final DoctorMapper doctorMapper;

    private final WorkScheduleMapper workScheduleMapper;

    private final DoctorHelper doctorHelper;

    private final DoctorRepository doctorRepository;

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Autowired
    public DoctorService(DoctorMapper doctorMapper,
                         WorkScheduleMapper workScheduleMapper,
                         DoctorHelper doctorHelper,
                         DoctorRepository doctorRepository) {
        this.doctorMapper = doctorMapper;
        this.workScheduleMapper = workScheduleMapper;
        this.doctorHelper = doctorHelper;
        this.doctorRepository = doctorRepository;
    }

    public PageDTO<DoctorDTO> searchDoctors(SearchRequest request, Pageable pageable) {
        Page<Doctor> result = doctorRepository.findAllByFirstnameOrLastname(request.getQuery(), request.getQuery(), pageable);

        return PageDTO.<DoctorDTO>builder()
                .content(doctorMapper.map(result.getContent()))
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .build();
    }

    public Collection<DoctorDTO> getDoctors() {
        return doctorMapper.map(this.doctorRepository.findAll());
    }

    public DoctorDTO getDoctorBy(Long doctorID) throws DoctorNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(doctorID);
        if (doctor.isPresent())
            return doctorMapper.map(doctor.get());
        else
            throw new DoctorNotFoundException(doctorID);
    }

    @Transactional
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        return doctorMapper.map(doctorRepository.save(doctorHelper.setDoctor(doctorDTO)));
    }
    @Transactional
    public DoctorDTO updateDoctor(Long doctorID, DoctorDTO doctorDTO) throws DoctorNotFoundException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            doctorHelper.updateDoctor(doctorDTO, doctor);

            return doctorMapper.map(doctorRepository.save(doctor));
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    @Transactional
    public DoctorDTO replaceDoctor(Long doctorID, DoctorDTO doctorDTO) throws DoctorNotFoundException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            doctorHelper.updateDoctor(doctorDTO, doctor);

            return doctorMapper.map(doctorRepository.save(doctor));
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    public Boolean deleteDoctorByID(Long doctorID) throws DoctorNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(doctorID);
        if (optionalDoctor.isPresent()) {
            doctorRepository.deleteById(doctorID);
            return true;
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    public Collection<WorkScheduleDTO> getWorkScheduleDTOSByDoctorID(Long doctorID) throws ResourceNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            return workScheduleMapper.map(doctor.getWorkSchedule());
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    @Transactional
    public Collection<WorkScheduleDTO> addWorkScheduleByID(Long doctorID,
                                                           WorkScheduleDTO workScheduleDTO) throws ResourceNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            doctor.getWorkSchedule().add(workScheduleMapper.toEntity(workScheduleDTO));

            return workScheduleMapper.map(doctorRepository.save(doctor).getWorkSchedule());
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    @Transactional
    public Collection<WorkScheduleDTO> replaceWorkScheduleByID(Long doctorID,
                                                               Collection<WorkScheduleDTO> workScheduleDTOS) throws ResourceNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.getWorkSchedule().clear();
            doctor.getWorkSchedule().addAll(workScheduleMapper.toEntities(workScheduleDTOS));

            return workScheduleMapper.map(doctorRepository.save(doctor).getWorkSchedule());
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    @Transactional
    public Collection<WorkScheduleDTO> deleteWorkScheduleByID(Long doctorID,
                                                              Long workScheduleID) throws ResourceNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            Collection<WorkSchedule> workSchedules = doctor.getWorkSchedule();

            workSchedules.remove(workScheduleRepository.getById(workScheduleID));

            return workScheduleMapper.map(doctorRepository.save(doctor).getWorkSchedule());
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

}
