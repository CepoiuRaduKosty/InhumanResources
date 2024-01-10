package com.ihr.ihr.common.interf.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.entities.User;

public interface UserEntityMapping {
    UserDto toUserDto(User user);

    UserCreationDto toUserCreationDto(User user);
}
