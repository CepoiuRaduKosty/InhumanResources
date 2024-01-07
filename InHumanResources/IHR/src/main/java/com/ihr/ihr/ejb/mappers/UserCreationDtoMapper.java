package com.ihr.ihr.ejb.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.interf.mappers.UserCreationDtoMapping;

public class UserCreationDtoMapper implements UserCreationDtoMapping {

    @Override
    public UserDto toUserDto(UserCreationDto userCreationDto) {
        return new UserDto(
                null,
                userCreationDto.getEmail(),
                userCreationDto.getPassword(),
                userCreationDto.getUsername()
        );
    }
}
