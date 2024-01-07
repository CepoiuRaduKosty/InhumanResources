package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.excep.InvalidUserException;
import com.ihr.ihr.common.excep.UnknownUserException;
import com.ihr.ihr.common.interf.UserProvider;
import com.ihr.ihr.common.interf.mappers.UserCreationDtoMapping;
import com.ihr.ihr.common.interf.mappers.UserEntityMapping;
import com.ihr.ihr.common.interf.validators.UserValidation;
import com.ihr.ihr.entities.User;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.logging.Logger;

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

    private void copyUserCreationDtoToEntity(User destination, UserCreationDto source) {
        destination.setUsername(source.getUsername());
        destination.setPassword(source.getPassword());
        destination.setEmail(source.getEmail());
    }

    @Override
    public Long createUserByDto(UserCreationDto userCreationDto) throws InvalidUserException {
        if(!userValidation.isUserCreationDtoValid(userCreationDto))
            throw new InvalidUserException();
        User user = userCreationDtoMapping.toUserEntity(userCreationDto);
        entityManager.persist(user);
        return user.getId();
    }

    @Override
    public UserDto getUserDtoById(Long userID) {
        User foundUser = entityManager.find(User.class, userID);
        if(foundUser == null)
            return null;
        return userEntityMapping.toUserDto(foundUser);
    }

    @Override
    public void updateUserById(Long userID, UserCreationDto userCreationDto) throws UnknownUserException, InvalidUserException {
        if(userValidation.isUserCreationDtoValid(userCreationDto))
            throw new InvalidUserException();

        User user = entityManager.find(User.class, userID);
        if(user == null)
            throw new UnknownUserException();

        copyUserCreationDtoToEntity(user, userCreationDto);
    }

    @Override
    public void deleteUserById(Long userID) throws UnknownUserException {
        User user = entityManager.find(User.class, userID);
        if(user == null)
            throw new UnknownUserException();

        entityManager.remove(user);
    }

}
