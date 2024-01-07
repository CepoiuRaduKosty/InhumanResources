package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;

public interface UserProvider {
    Long createUserByDto(UserCreationDto userCreationDto);
}
