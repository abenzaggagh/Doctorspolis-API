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
import com.doctorspolis.backend.utility.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class DoctorService extends AbstractService implements CRUDService<Doctor, DoctorDTO> {

    private final DoctorMapper doctorMapper;

    private final WorkScheduleMapper workScheduleMapper;

    private final DoctorHelper doctorHelper;

    private final DoctorRepository doctorRepository;

    private final WorkScheduleRepository workScheduleRepository;

    @Autowired
    public DoctorService(DoctorMapper doctorMapper,
                         DoctorHelper doctorHelper,
                         DoctorRepository doctorRepository,
                         WorkScheduleMapper workScheduleMapper,
                         WorkScheduleRepository workScheduleRepository) {
        this.doctorMapper = doctorMapper;
        this.doctorHelper = doctorHelper;
        this.doctorRepository = doctorRepository;
        this.workScheduleMapper = workScheduleMapper;
        this.workScheduleRepository = workScheduleRepository;
    }

    public PageDTO<DoctorDTO> searchDoctors(SearchRequest request, Pageable pageable) {
        Page<Doctor> result = doctorRepository.findAllByFirstnameOrLastname(request.getQuery(), request.getQuery(), pageable);

        return PageDTO.<DoctorDTO>builder()
                .content(doctorMapper.toDTOs(result.getContent()))
                .totalPages(result.getTotalPages())
                .totalElements(result.getTotalElements())
                .build();
    }

    @Override
    public Collection<DoctorDTO> allDTOs() {
        return doctorMapper.toDTOs(this.allEntities());
    }

    @Override
    public Collection<Doctor> allEntities() {
        return this.doctorRepository.findAll();
    }

    @Override
    public DoctorDTO oneDTO(Long doctorID) throws DoctorNotFoundException {
        return doctorMapper.toDTO(this.oneEntity(doctorID));
    }

    @Override
    public Doctor oneEntity(Long doctorID) throws DoctorNotFoundException {
        Optional<Doctor> doctor = doctorRepository.findById(doctorID);
        if (doctor.isPresent())
            return doctor.get();
        else
            throw new DoctorNotFoundException(doctorID);
    }

    @Override
    @Transactional
    public DoctorDTO create(DoctorDTO doctorDTO) {
        return doctorMapper.toDTO(doctorRepository.save(doctorHelper.setDoctor(doctorDTO)));
    }

    @Override
    @Transactional
    public DoctorDTO updateByID(Long doctorID, DoctorDTO doctorDTO) throws DoctorNotFoundException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            doctorHelper.updateDoctor(doctorDTO, doctor);

            return doctorMapper.toDTO(doctorRepository.save(doctor));
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    @Transactional
    public DoctorDTO replaceDoctor(Long doctorID, DoctorDTO doctorDTO) throws DoctorNotFoundException {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            doctorHelper.replaceDoctor(doctorDTO, doctor);

            return doctorMapper.toDTO(doctorRepository.save(doctor));
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    @Override
    public Boolean deleteByID(Long doctorID) throws DoctorNotFoundException {
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

            return workScheduleMapper.toDTOs(doctor.getWorkSchedule());
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

            return workScheduleMapper.toDTOs(doctorRepository.save(doctor).getWorkSchedule());
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

            return workScheduleMapper.toDTOs(doctorRepository.save(doctor).getWorkSchedule());
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

    // TODO: Refactor this method with clean code approach
    @Transactional
    public WorkScheduleDTO editWorkScheduleByID(Long doctorID,
                                                Long workScheduleID,
                                                WorkScheduleDTO workScheduleDTO) throws ResourceNotFoundException {
        Optional<Doctor> optionalDoctor = this.doctorRepository.findById(doctorID);

        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();

            Optional<WorkSchedule> workScheduleOptional = workScheduleRepository.findById(workScheduleID);

            if (workScheduleOptional.isPresent()) {
                WorkSchedule workSchedule = workScheduleOptional.get();
                WorkSchedule newWS = workScheduleMapper.toEntity(workScheduleDTO);

                doctorHelper.updateWorkSchedule(newWS, workSchedule);

                workScheduleRepository.save(workSchedule);
                Doctor newValues = doctorRepository.save(doctor);
                // Fix the value returned
                return workScheduleMapper.toDTO(workSchedule);
            } else {
                throw new ResourceNotFoundException(workScheduleID.toString());
            }

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

            return workScheduleMapper.toDTOs(doctorRepository.save(doctor).getWorkSchedule());
        } else {
            throw new DoctorNotFoundException(doctorID);
        }
    }

}
