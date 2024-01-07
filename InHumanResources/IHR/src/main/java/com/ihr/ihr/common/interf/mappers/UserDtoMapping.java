package com.ihr.ihr.common.interf.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;

public interface UserDtoMapping {
    UserCreationDto toUserCreationDto(UserDto userDto);
}
