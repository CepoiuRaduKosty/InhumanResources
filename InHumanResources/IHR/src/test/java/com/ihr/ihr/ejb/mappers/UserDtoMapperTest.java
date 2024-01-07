package com.ihr.ihr.ejb.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDtoMapperTest {

    @InjectMocks
    UserDtoMapper userDtoMapper;

    @Test
    void testToUserCreationDto() {
        UserDto userDto = new UserDto(12L, "test@example.com", "password123", "testUser");

        UserCreationDto userCreationDto = userDtoMapper.toUserCreationDto(userDto);

        assertEquals(userDto.getEmail(), userCreationDto.getEmail());
        assertEquals(userDto.getPassword(), userCreationDto.getPassword());
        assertEquals(userDto.getUsername(), userCreationDto.getUsername());
    }

}