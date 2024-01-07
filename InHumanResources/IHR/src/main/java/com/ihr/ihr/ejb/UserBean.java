package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.UserDtos.UserCreationDto;
import com.ihr.ihr.common.interf.UserProvider;
import com.ihr.ihr.common.interf.mappers.UserCreationDtoMapping;
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

    @Override
    public Long createUserByDto(UserCreationDto userCreationDto) {
        User user = userCreationDtoMapping.toUserEntity(userCreationDto);
        entityManager.persist(user);
        return user.getId();
    }


}
