package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.excep.PayDayAlreadyExistsException;
import com.ihr.ihr.common.excep.PayDayDoesNotExistException;
import com.ihr.ihr.common.interf.PayDayProvider;
import com.ihr.ihr.common.interf.PayDayValidation;
import com.ihr.ihr.entities.BankInfo;
import com.ihr.ihr.entities.PayDay;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ValidationException;

import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PayDayBean implements PayDayProvider {
    private static final Logger LOG = Logger.getLogger(BankInfoBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    PayDayValidation payDayValidation;

    @Override
    public Integer getDayOfMonth() {
        LOG.info("getDayOfMonth");

        try {
            List<PayDay> payDays = entityManager.createQuery("SELECT b FROM PayDay b", PayDay.class).getResultList();
            if(payDays.isEmpty())
                return null;
            else
                return payDays.get(0).getDayOfMonth();
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public void addPayDayOfMonth(Integer dayOfMonth) throws ValidationException, PayDayAlreadyExistsException {
        if(isPayDateSet())
            throw new PayDayAlreadyExistsException("");
        if(!payDayValidation.isPayDayValid(dayOfMonth))
            throw new ValidationException("Payday not valid");

        PayDay payDay = new PayDay();
        payDay.setDayOfMonth(dayOfMonth);
        entityManager.persist(payDay);
    }

    @Override
    public void updatePayDay(Integer dayOfMonth) throws ValidationException, PayDayDoesNotExistException {
        if(!isPayDateSet())
            throw new PayDayDoesNotExistException("");
        if(!payDayValidation.isPayDayValid(dayOfMonth))
            throw new ValidationException("Payday not valid");

        PayDay payDay = entityManager.createQuery("SELECT b FROM PayDay b", PayDay.class).getSingleResult();
        payDay.setDayOfMonth(dayOfMonth);
    }

    @Override
    public boolean isPayDateSet() {
        LOG.info("isPayDateSet");

        try {
            List<PayDay> payDays = entityManager.createQuery("SELECT b FROM PayDay b", PayDay.class).getResultList();
            return !payDays.isEmpty();
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
}
