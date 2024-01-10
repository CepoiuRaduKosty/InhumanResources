package com.ihr.ihr.common.interf.validators;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.entities.User;

public interface UserValidation {
    boolean isUsernameValid(String username);

    boolean isEmailValid(String email);

    boolean isPasswordValid(String password);

    boolean isUserCreationDtoValid(UserCreationDto userCreationDto);

    boolean isUserDtoValid(UserDto userDto);

    boolean isUserEntityValid(User user);

    boolean isUserCreationDtoValidNoPasswordCheck(UserCreationDto userCreationDto);

    boolean isUserDtoValidNoPasswordCheck(UserDto userDto);

    boolean isUserEntityValidNoPasswordCheck(User user);
}
