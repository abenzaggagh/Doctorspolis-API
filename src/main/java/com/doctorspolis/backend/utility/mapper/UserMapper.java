package com.doctorspolis.backend.utility.mapper;

import com.doctorspolis.backend.model.DTO.UserDTO;
import com.doctorspolis.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring",
        uses = { PatientMapper.class, DoctorMapper.class }
)
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

}
