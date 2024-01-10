package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.excep.InvalidUserException;
import com.ihr.ihr.common.excep.UnknownEmployeeException;
import com.ihr.ihr.common.excep.UnknownUserException;
import jakarta.transaction.Transactional;

import java.util.Collection;

public interface UserProvider {

    Long createUserByDto(UserCreationDto userCreationDto, Collection<String> groups) throws InvalidUserException;

    UserDto getUserDtoById(Long userID);

    void updateUserById(Long userID, UserCreationDto userCreationDto) throws UnknownUserException, InvalidUserException;

    void deleteUserById(Long userID) throws UnknownUserException;

    void setEmployeeToUser(Long userID, Long employeeID) throws UnknownUserException, UnknownEmployeeException;

    @Transactional
    void removeUserGroupByUsername(String username);

    UserDto getUserDtoByUsername(String username);
}
