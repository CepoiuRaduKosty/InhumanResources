package com.ihr.ihr.ejb.validators;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.interf.mappers.UserDtoMapping;
import com.ihr.ihr.common.interf.mappers.UserEntityMapping;
import com.ihr.ihr.common.interf.validators.UserValidation;
import com.ihr.ihr.entities.User;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class UserValidator implements UserValidation {
    private static final Logger LOG = Logger.getLogger(UserValidator.class.getName());

    @Inject
    UserDtoMapping userDtoMapping;

    @Inject
    UserEntityMapping userEntityMapping;

    @Override
    public boolean isUsernameValid(String username) {
        return !username.isEmpty() && !username.isBlank();
    }

    @Override
    public boolean isEmailValid(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    @Override
    public boolean isPasswordValid(String password) {
        return !password.isEmpty() && !password.isBlank();
    }

    @Override
    public boolean isUserCreationDtoValid(UserCreationDto userCreationDto) {
        if(!isEmailValid(userCreationDto.getEmail()))
            return false;
        if(!isPasswordValid(userCreationDto.getPassword()))
            return false;
        return isUsernameValid(userCreationDto.getUsername());
    }

    @Override
    public boolean isUserDtoValid(UserDto userDto) {
        return isUserCreationDtoValid(userDtoMapping.toUserCreationDto(userDto));
    }

    @Override
    public boolean isUserEntityValid(User user) {
        return isUserCreationDtoValid(userEntityMapping.toUserCreationDto(user));
    }
}
