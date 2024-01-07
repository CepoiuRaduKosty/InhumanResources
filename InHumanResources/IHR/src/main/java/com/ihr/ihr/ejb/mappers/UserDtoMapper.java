package com.ihr.ihr.ejb.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.interf.mappers.UserDtoMapping;

public class UserDtoMapper implements UserDtoMapping {

    @Override
    public UserCreationDto toUserCreationDto(UserDto userDto) {
        return new UserCreationDto(
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getUsername()
        );
    }
}
