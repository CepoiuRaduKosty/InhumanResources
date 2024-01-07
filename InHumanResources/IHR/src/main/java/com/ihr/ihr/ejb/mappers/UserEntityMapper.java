package com.ihr.ihr.ejb.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.interf.mappers.UserEntityMapping;
import com.ihr.ihr.entities.User;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;

@Stateless
public class UserEntityMapper implements UserEntityMapping {
    private static final Logger LOG = Logger.getLogger(UserEntityMapper.class.getName());

    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getUsername()
        );
        if(user.getEmployee() != null) {
            userDto.setEmployeeId(user.getEmployee().getId());
        }
        return userDto;
    }

    @Override
    public UserCreationDto toUserCreationDto(User user) {
        return new UserCreationDto(
                user.getEmail(),
                user.getPassword(),
                user.getUsername()
        );
    }
}
