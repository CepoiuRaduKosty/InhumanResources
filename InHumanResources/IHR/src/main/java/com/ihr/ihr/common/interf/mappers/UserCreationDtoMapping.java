package com.ihr.ihr.common.interf.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.entities.User;
import jakarta.servlet.http.HttpServletRequest;

public interface UserCreationDtoMapping {
    UserDto toUserDto (UserCreationDto userCreationDto);

    User toUserEntity(UserCreationDto userCreationDto);

    UserCreationDto fromRequest(HttpServletRequest request);
}
