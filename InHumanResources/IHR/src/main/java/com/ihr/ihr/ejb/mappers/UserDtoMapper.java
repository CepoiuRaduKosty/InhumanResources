package com.ihr.ihr.ejb.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.interf.PasswordProvider;
import com.ihr.ihr.common.interf.mappers.UserDtoMapping;
import com.ihr.ihr.entities.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.logging.Logger;

@Stateless
public class UserDtoMapper implements UserDtoMapping {
    private static final Logger LOG = Logger.getLogger(UserDtoMapper.class.getName());

    @Inject
    PasswordProvider passwordProvider;

    @Override
    public UserCreationDto toUserCreationDto(UserDto userDto) {
        return new UserCreationDto(
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getUsername()
        );
    }

    @Override
    public User toUserEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(passwordProvider.convertToSha256(userDto.getPassword()));
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
