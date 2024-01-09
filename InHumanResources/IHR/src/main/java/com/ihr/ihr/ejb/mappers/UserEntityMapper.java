package com.ihr.ihr.ejb.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.interf.PasswordProvider;
import com.ihr.ihr.common.interf.mappers.UserEntityMapping;
import com.ihr.ihr.ejb.PasswordBean;
import com.ihr.ihr.entities.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.logging.Logger;

@Stateless
public class UserEntityMapper implements UserEntityMapping {
    private static final Logger LOG = Logger.getLogger(UserEntityMapper.class.getName());

    @Inject
    PasswordProvider passwordProvider;

    @Override
    public UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(
                user.getId(),
                user.getEmail(),
                passwordProvider.convertToSha256(user.getPassword()),
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
                passwordProvider.convertToSha256(user.getPassword()),
                user.getUsername()
        );
    }
}
