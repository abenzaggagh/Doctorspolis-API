package com.doctorspolis.utility.mapper;

import com.doctorspolis.model.data.authentication.User;
import com.doctorspolis.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring"
)
public interface UserMapper {

    UserDTO toDTO(User user);

}
