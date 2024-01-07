package com.ihr.ihr.ejb.mappers;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserEntityMapperTest {

    @InjectMocks
    UserEntityMapper userEntityMapper;

    @Test
    void testToUserDto() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setUsername("testUser");

        UserDto userDto = userEntityMapper.toUserDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertNull(userDto.getEmployeeId());
    }

    @Test
    void testToUserDto_withEmployee() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setUsername("testUser");
        Employee employee = new Employee();
        employee.setId(2L);
        user.setEmployee(employee);

        UserDto userDto = userEntityMapper.toUserDto(user);

        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getEmail(), userDto.getEmail());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(2L, userDto.getEmployeeId());
    }

    @Test
    void testToUserCreationDto() {
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setUsername("testUser");
        Employee employee = new Employee();
        employee.setId(2L);
        user.setEmployee(employee);

        UserCreationDto userCreationDto = userEntityMapper.toUserCreationDto(user);

        assertEquals(user.getEmail(), userCreationDto.getEmail());
        assertEquals(user.getPassword(), userCreationDto.getPassword());
        assertEquals(user.getUsername(), userCreationDto.getUsername());
    }

}