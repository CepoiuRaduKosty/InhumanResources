package com.ihr.ihr.ejb;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.excep.InvalidUserException;
import com.ihr.ihr.common.excep.UnknownEmployeeException;
import com.ihr.ihr.common.excep.UnknownUserException;
import com.ihr.ihr.common.interf.validators.UserValidation;
import com.ihr.ihr.ejb.mappers.UserCreationDtoMapper;
import com.ihr.ihr.ejb.mappers.UserDtoMapper;
import com.ihr.ihr.ejb.mappers.UserEntityMapper;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.User;

import jakarta.persistence.EntityManager;

@ExtendWith(MockitoExtension.class)
class UserBeanTest {

    @Mock
    EntityManager entityManager;

    @Mock
    UserCreationDtoMapper userCreationDtoMapper;

    @Mock
    UserDtoMapper userDtoMapper;

    @Mock
    UserEntityMapper userEntityMapper;

    @Mock
    UserValidation userValidation;

    @InjectMocks
    UserBean userBean;

    @Test
    public void createUserByDto_ValidUser_ReturnsValidId() throws InvalidUserException {
        UserCreationDto validDto = new UserCreationDto("test@example.com", "password123", "testUser");
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("password123");
        mockUser.setUsername("testUser");

        when(userValidation.isUserCreationDtoValid(any(UserCreationDto.class))).thenReturn(true);
        when(userCreationDtoMapper.toUserEntity(validDto)).thenReturn(mockUser);

        Long userId = userBean.createUserByDto(validDto);

        assertNotNull(userId);
        assertEquals(1L, userId);

        verify(entityManager).persist(argThat(argument -> {
            User userEntity = (User) argument;
            return userEntity.getEmail().equals(validDto.getEmail()) &&
                    userEntity.getPassword().equals(validDto.getPassword()) &&
                    userEntity.getUsername().equals(validDto.getUsername());
        }));
    }

    @Test
    public void createUserByDto_InvalidUser_ThrowsInvalidUserException() {
        UserCreationDto invalidDto = new UserCreationDto("", "weakpass", "  ");

        when(userValidation.isUserCreationDtoValid(invalidDto)).thenReturn(false);

        assertThrows(InvalidUserException.class, () -> userBean.createUserByDto(invalidDto));
    }

    @Test
    public void getUserDtoById_ValidUserId_ReturnsUserDto() {
        Long validUserId = 123L;
        User userEntity = new User();
        userEntity.setId(validUserId);
        userEntity.setEmail("test@example.com");
        userEntity.setUsername("test_user");
        userEntity.setPassword("strongPassword");

        when(entityManager.find(User.class, validUserId)).thenReturn(userEntity);
        when(userEntityMapper.toUserDto(userEntity)).thenReturn(new UserDto(validUserId, "test@example.com", "strongPassword", "test_user"));

        UserDto userDto = userBean.getUserDtoById(validUserId);

        assertNotNull(userDto);
        assertEquals(validUserId, userDto.getId());
        assertEquals("test@example.com", userDto.getEmail());
        assertEquals("strongPassword", userDto.getPassword());
        assertEquals("test_user", userDto.getUsername());
    }

    @Test
    public void getUserDtoById_InvalidUserId_ReturnsNull() {
        Long invalidUserId = 999L;

        when(entityManager.find(User.class, invalidUserId)).thenReturn(null);

        UserDto userDto = userBean.getUserDtoById(invalidUserId);

        assertNull(userDto);
    }

    @Test
    public void updateUserById_ValidUserDetails_UserUpdated() throws UnknownUserException, InvalidUserException {
        Long userId = 123L;
        UserCreationDto validUserCreationDto = new UserCreationDto("newemail@example.com", "newpassword", "newusername");

        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("oldemail@example.com");
        existingUser.setPassword("oldpassword");
        existingUser.setUsername("oldusername");

        when(entityManager.find(User.class, userId)).thenReturn(existingUser);
        when(userValidation.isUserCreationDtoValid(validUserCreationDto)).thenReturn(false);

        userBean.updateUserById(userId, validUserCreationDto);

        verify(entityManager).find(User.class, userId);
        verify(userValidation).isUserCreationDtoValid(validUserCreationDto);
        assertEquals("newemail@example.com", existingUser.getEmail());
        assertEquals("newpassword", existingUser.getPassword());
        assertEquals("newusername", existingUser.getUsername());
    }

    @Test
    public void updateUserById_InvalidUserId_ThrowsUnknownUserException(){
        Long userId = 123L;
        UserCreationDto validUserCreationDto = new UserCreationDto("newemail@example.com", "newpassword", "newusername");

        when(entityManager.find(User.class, userId)).thenReturn(null);

        assertThrows(UnknownUserException.class, () -> userBean.updateUserById(userId, validUserCreationDto));
    }

    @Test
    public void updateUserById_InvalidUserDetails_ThrowsInvalidUserException(){
        Long userId = 123L;
        UserCreationDto invalidUserCreationDto = new UserCreationDto("", "", "");

        when(userValidation.isUserCreationDtoValid(invalidUserCreationDto)).thenReturn(true);

        assertThrows(InvalidUserException.class, () -> userBean.updateUserById(userId, invalidUserCreationDto));
    }

    @Test
    public void deleteUserById_ValidUserId_DeletesUser() throws UnknownUserException {
        Long userId = 123L;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setEmail("email@example.com");
        existingUser.setPassword("password");
        existingUser.setUsername("username");

        when(entityManager.find(User.class, userId)).thenReturn(existingUser);

        userBean.deleteUserById(userId);

        verify(entityManager).remove(existingUser);
    }

    @Test
    public void deleteUserById_InvalidUserId_ThrowsUnknownUserException() {
        Long userId = 123L;
        when(entityManager.find(User.class, userId)).thenReturn(null);

        assertThrows(UnknownUserException.class, () -> userBean.deleteUserById(userId));
    }

    @Test
    public void setEmployeeToUser_ValidIds_SuccessfullySetsEmployee() {
        Long userId = 123L;
        Long employeeId = 456L;

        User user = new User();
        Employee employee = new Employee();

        when(entityManager.merge(any(User.class))).thenReturn(user);
        when(entityManager.find(User.class, userId)).thenReturn(user);
        when(entityManager.find(Employee.class, employeeId)).thenReturn(employee);

        assertDoesNotThrow(() -> userBean.setEmployeeToUser(userId, employeeId));

        assertEquals(employee, user.getEmployee());
        verify(entityManager).merge(user);
    }

    @Test
    public void setEmployeeToUser_UnknownUser_ExceptionThrown() {
        Long userId = 123L;
        Long employeeId = 456L;

        when(entityManager.find(User.class, userId)).thenReturn(null);

        assertThrows(UnknownUserException.class, () -> userBean.setEmployeeToUser(userId, employeeId));
    }

    @Test
    public void setEmployeeToUser_UnknownEmployee_ExceptionThrown() {
        Long userId = 123L;
        Long employeeId = 456L;

        when(entityManager.find(User.class, userId)).thenReturn(new User());
        when(entityManager.find(Employee.class, employeeId)).thenReturn(null);

        assertThrows(UnknownEmployeeException.class, () -> userBean.setEmployeeToUser(userId, employeeId));
    }

    @Test
    void testDeleteUserWithEmployee() throws UnknownUserException {
        User mockUser = new User();
        Employee mockEmployee = new Employee();
        mockUser.setEmployee(mockEmployee);
        mockEmployee.setUser(mockUser);

        when(entityManager.find(User.class, 1L)).thenReturn(mockUser);

        userBean.deleteUserById(1L);

        verify(entityManager, times(1)).remove(mockUser);
        assertNull(mockEmployee.getUser());
    }
}