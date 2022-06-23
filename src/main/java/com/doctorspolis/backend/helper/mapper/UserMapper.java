package com.doctorspolis.backend.helper.mapper;

import com.doctorspolis.backend.model.DTO.UserDTO;
import com.doctorspolis.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

}
