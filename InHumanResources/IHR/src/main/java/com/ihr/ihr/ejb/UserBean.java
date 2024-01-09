package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.excep.InvalidUserException;
import com.ihr.ihr.common.excep.UnknownEmployeeException;
import com.ihr.ihr.common.excep.UnknownUserException;
import com.ihr.ihr.common.interf.EmployeeProvider;
import com.ihr.ihr.common.interf.PasswordProvider;
import com.ihr.ihr.common.interf.UserProvider;
import com.ihr.ihr.common.interf.mappers.UserCreationDtoMapping;
import com.ihr.ihr.common.interf.mappers.UserEntityMapping;
import com.ihr.ihr.common.interf.validators.UserValidation;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.User;
import com.ihr.ihr.entities.UserGroup;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.transaction.Transactional;

import java.util.Collection;
import java.util.logging.Logger;

@Stateless
public class UserBean implements UserProvider {
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    UserCreationDtoMapping userCreationDtoMapping;

    @Inject
    UserEntityMapping userEntityMapping;

    @Inject
    UserValidation userValidation;

    @Inject
    EmployeeProvider employeeProvider;

    @Inject
    PasswordProvider passwordProvider;

    private void copyUserCreationDtoToEntity(User destination, UserCreationDto source) {
        destination.setUsername(source.getUsername());
        if(!source.getPassword().isBlank() && !source.getPassword().isEmpty())
            destination.setPassword(passwordProvider.convertToSha256(source.getPassword()));
        destination.setEmail(source.getEmail());
    }

    private User safeGetUserEntityById(Long userID) throws UnknownUserException {
        User user = entityManager.find(User.class, userID);
        if (user == null)
            throw new UnknownUserException();
        return user;
    }

    private Employee safeGetEmployeeEntityById(Long employeeID) throws UnknownEmployeeException {
        Employee employee = entityManager.find(Employee.class, employeeID);
        if (employee == null)
            throw new UnknownEmployeeException();
        return employee;
    }

    @Override
    public Long createUserByDto(UserCreationDto userCreationDto, Collection<String> groups) throws InvalidUserException {
        if (!userValidation.isUserCreationDtoValid(userCreationDto))
            throw new InvalidUserException();
        User user = userCreationDtoMapping.toUserEntity(userCreationDto);
        entityManager.persist(user);

        assignGroupsToUser(userCreationDto.getUsername(), groups);

        return user.getId();
    }

    private void assignGroupsToUser(String username, Collection<String> groups) {
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }

    @Override
    public UserDto getUserDtoById(Long userID) {
        try {
            User foundUser = safeGetUserEntityById(userID);
            return userEntityMapping.toUserDto(foundUser);
        } catch (UnknownUserException ex) {
            return null;
        }
    }

    private void checkPasswordFieldValidForUpdates(UserCreationDto userCreationDto) throws InvalidUserException {
        String passwordFieldTxt = userCreationDto.getPassword();
        if((passwordFieldTxt.isEmpty() || passwordFieldTxt.isBlank()) && !userValidation.isUserCreationDtoValidNoPasswordCheck(userCreationDto))
            throw new InvalidUserException();
        if(!passwordFieldTxt.isEmpty() && !passwordFieldTxt.isBlank() && !userValidation.isUserCreationDtoValid(userCreationDto))
            throw new InvalidUserException();
    }

    @Override
    public void updateUserById(Long userID, UserCreationDto userCreationDto)
            throws UnknownUserException, InvalidUserException {
        checkPasswordFieldValidForUpdates(userCreationDto);
        User user = safeGetUserEntityById(userID);
        copyUserCreationDtoToEntity(user, userCreationDto);
    }

    @Override
    public void deleteUserById(Long userID) throws UnknownUserException {
        User user = safeGetUserEntityById(userID);
        if(user.getEmployee() != null) {
            Employee employee = user.getEmployee();
            employee.setUser(null);
            entityManager.merge(employee);
            employeeProvider.deleteEmployeeById(employee.getId());
        }
        removeUserGroupByUsername(user.getUsername());

        entityManager.remove(user);
    }

    @Override
    public void setEmployeeToUser(Long userID, Long employeeID) throws UnknownUserException, UnknownEmployeeException {
        User user = safeGetUserEntityById(userID);
        Employee employee = safeGetEmployeeEntityById(employeeID);

        user.setEmployee(employee);
        entityManager.merge(user);
    }

    @Transactional
    public void removeUserGroupByUsername(String username) {
        entityManager.createQuery("DELETE FROM UserGroup u WHERE u.username = :username")
                .setParameter("username", username)
                .executeUpdate();
    }

}
