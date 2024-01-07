package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.UserDtos.UserDto;

import java.util.List;

public interface UserProvider {
    List<UserDto> findAllUsers();
}
