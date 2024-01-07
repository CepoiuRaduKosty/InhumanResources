package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.UserDtos.UserDto;
import com.ihr.ihr.common.interf.UserProvider;
import com.ihr.ihr.entities.User;
import jakarta.ejb.EJBException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.logging.Logger;

public class UserBean implements UserProvider {
    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserDto> findAllUsers() {
        try {
            LOG.info("findAllUsers");
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return null;
        }
        catch (Exception ex) {
            throw new EJBException(ex);
        }
    }


}
