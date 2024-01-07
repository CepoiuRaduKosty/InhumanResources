package com.ihr.ihr.ejb.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.interf.mappers.UserCreationDtoMapping;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;

@Stateless
public class UserCreationDtoMapper implements UserCreationDtoMapping {
    private static final Logger LOG = Logger.getLogger(UserCreationDtoMapper.class.getName());

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
