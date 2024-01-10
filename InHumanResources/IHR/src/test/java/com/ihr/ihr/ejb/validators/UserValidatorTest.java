package com.ihr.ihr.ejb.validators;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.ejb.mappers.UserDtoMapper;
import com.ihr.ihr.ejb.mappers.UserEntityMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.entities.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    UserDtoMapper userDtoMapper;

    @Mock
    UserEntityMapper userEntityMapper;

    @InjectMocks
    UserValidator userValidator;

    @Test
    void testIsUsernameValid() {
        UserValidator validator = new UserValidator();

        assertTrue(validator.isUsernameValid("JohnDoe"));
        assertTrue(validator.isUsernameValid("Alice123"));
        assertFalse(validator.isUsernameValid(""));
        assertFalse(validator.isUsernameValid("   "));
    }

    @Test
    void testIsEmailValid() {
        UserValidator validator = new UserValidator();

        assertTrue(validator.isEmailValid("test@example.com"));
        assertTrue(validator.isEmailValid("user@mail.co"));
        assertFalse(validator.isEmailValid("invalid_email.com"));
        assertFalse(validator.isEmailValid("noatsymbol.com"));
    }

    @Test
    void testIsPasswordValid() {
        UserValidator validator = new UserValidator();

        assertTrue(validator.isPasswordValid("pass123"));
        assertTrue(validator.isPasswordValid("mySecret!"));
        assertFalse(validator.isPasswordValid(""));
        assertFalse(validator.isPasswordValid("    "));
    }

    @Test
    void testIsUserCreationDtoValid() {
        UserValidator validator = new UserValidator();
        UserCreationDto validDto = new UserCreationDto("test@example.com", "pass123", "JohnDoe");
        UserCreationDto invalidDto = new UserCreationDto("invalid_email.com", "", "");

        assertTrue(validator.isUserCreationDtoValid(validDto));
        assertFalse(validator.isUserCreationDtoValid(invalidDto));
    }

    @Test
    void testIsUserDtoValid() {
        UserDto validUserDto = new UserDto(1L, "test@example.com", "pass123", "JohnDoe");

        when(userDtoMapper.toUserCreationDto(validUserDto))
                .thenReturn(new UserCreationDto("test@example.com", "pass123", "JohnDoe"));
        assertTrue(userValidator.isUserDtoValid(validUserDto));

        UserDto invalidUserDto = new UserDto(null, "invalid_email.com", "", "");

        when(userDtoMapper.toUserCreationDto(invalidUserDto))
                .thenReturn(new UserCreationDto("invalid_email.com", "", ""));
        assertFalse(userValidator.isUserDtoValid(invalidUserDto));
    }

    @Test
    void testIsUserEntityValid() {
        User validUserEntity = new User();
        validUserEntity.setId(1L);
        validUserEntity.setEmail("test@example.com");
        validUserEntity.setPassword("pass123");
        validUserEntity.setUsername("JohnDoe");
        User invalidUserEntity = new User();
        invalidUserEntity.setEmail("invalid_email.com");
        invalidUserEntity.setPassword("");
        invalidUserEntity.setUsername("");

        when(userEntityMapper.toUserCreationDto(validUserEntity))
                .thenReturn(new UserCreationDto("test@example.com", "pass123", "JohnDoe"));
        when(userEntityMapper.toUserCreationDto(invalidUserEntity))
                .thenReturn(new UserCreationDto("invalid_email.com", "", ""));

        assertTrue(userValidator.isUserEntityValid(validUserEntity));
        assertFalse(userValidator.isUserEntityValid(invalidUserEntity));
    }

}