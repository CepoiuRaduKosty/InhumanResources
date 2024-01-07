package com.ihr.ihr.ejb.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserCreationDtoMapperTest {

    @InjectMocks
    UserCreationDtoMapper userCreationDtoMapper;

    @Test
    void testToUserDto() {
        UserCreationDto userCreationDto = new UserCreationDto("test@example.com", "password", "testuser");

        UserDto userDto = userCreationDtoMapper.toUserDto(userCreationDto);

        assertNotNull(userDto);
        assertEquals(userCreationDto.getEmail(), userDto.getEmail());
        assertEquals(userCreationDto.getPassword(), userDto.getPassword());
        assertEquals(userCreationDto.getUsername(), userDto.getUsername());
        assertNull(userDto.getId());
    }

    @Test
    void testToUserEntity() {
        UserCreationDto userCreationDto = new UserCreationDto("test@example.com", "password123", "testUser");

        User user = userCreationDtoMapper.toUserEntity(userCreationDto);

        assertEquals(userCreationDto.getEmail(), user.getEmail());
        assertEquals(userCreationDto.getPassword(), user.getPassword());
        assertEquals(userCreationDto.getUsername(), user.getUsername());
        assertNull(user.getId());
        assertNull(user.getEmployee());
    }
}