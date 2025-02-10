package com.doctorspolis.service;

import com.doctorspolis.model.data.User;
import com.doctorspolis.model.data.doctor.Doctor;
import com.doctorspolis.model.data.doctor.WorkPlace;
import com.doctorspolis.model.data.doctor.WorkSchedule;
import com.doctorspolis.model.dto.doctor.WorkPlaceDTO;
import com.doctorspolis.model.dto.doctor.WorkScheduleDTO;
import com.doctorspolis.repository.DoctorRepository;
import com.doctorspolis.repository.WorkPlaceRepository;
import com.doctorspolis.repository.WorkScheduleRepository;
import com.doctorspolis.utility.mapper.WorkPlaceMapper;
import com.doctorspolis.utility.mapper.WorkScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final WorkPlaceRepository workPlaceRepository;
    private final WorkScheduleRepository workScheduleRepository;

    private final WorkPlaceMapper workPlaceMapper;
    private final WorkScheduleMapper workScheduleMapper;


    public void workSchedule(User user, WorkScheduleDTO workScheduleDTO) {
        Doctor doctor = doctorRepository.findByUser(user);

        WorkSchedule workSchedule = workScheduleMapper.toEntity(workScheduleDTO);

        doctor.setWorkSchedule(workScheduleRepository.save(workSchedule));

        doctorRepository.save(doctor);
    }

    public void workPlace(User user, WorkPlaceDTO workPlaceDTO) {
        Doctor doctor = doctorRepository.findByUser(user);

        WorkPlace workPlace = workPlaceMapper.toEntity(workPlaceDTO);

        doctor.setWorkPlace(workPlaceRepository.save(workPlace));

        doctorRepository.save(doctor);
    }


}
