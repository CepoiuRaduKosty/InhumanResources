package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.excep.InvalidUserException;
import com.ihr.ihr.common.excep.UnknownUserException;

public interface UserProvider {
    Long createUserByDto(UserCreationDto userCreationDto) throws InvalidUserException;

    UserDto getUserDtoById(Long userID);

    void updateUserById(Long userID, UserCreationDto userCreationDto) throws UnknownUserException, InvalidUserException;

    void deleteUserById(Long userID) throws UnknownUserException;
}
