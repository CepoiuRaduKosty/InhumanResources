package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.PaycheckBonusDtos.CreatePaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.PaycheckBonusDto;
import com.ihr.ihr.common.excep.InvalidPaycheckBonusException;
import com.ihr.ihr.common.interf.BonusValidation;
import com.ihr.ihr.common.interf.PaycheckBonusProvider;
import com.ihr.ihr.entities.Paycheck;
import com.ihr.ihr.entities.PaycheckBonus;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PaycheckBonusBean implements PaycheckBonusProvider {
    private static final Logger LOG = Logger.getLogger(PaycheckBean.class.getName());

    @Inject
    BonusValidation bonusValidatorBean;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PaycheckBonusDto getPaycheckBonusDtoById(Long id) {
        LOG.info("getPaycheckBonusDtoById");

        try {
            PaycheckBonus paycheckBonus = entityManager.find(PaycheckBonus.class, id);

            return copyPaycheckBonusToDto(paycheckBonus);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public Long createPaycheckBonus(CreatePaycheckBonusDto createPaycheckBonusDto) throws InvalidPaycheckBonusException {
        LOG.info("createPaycheckBonus");

        if(!bonusValidatorBean.isPaycheckBonusDtoValid(createPaycheckBonusDto))
            throw new InvalidPaycheckBonusException();

        Paycheck paycheck = entityManager.find(Paycheck.class, createPaycheckBonusDto.getPaycheckId());

        PaycheckBonus paycheckBonus = new PaycheckBonus();

        paycheckBonus.setBonusDescription(createPaycheckBonusDto.getBonusDescription());
        paycheckBonus.setPaycheck(paycheck);
        paycheckBonus.setValue(createPaycheckBonusDto.getValue());

        entityManager.persist(paycheckBonus);

        return paycheckBonus.getId();
    }

    @Override
    public void updatePaycheckBonusById(Long paycheckBonusId, CreatePaycheckBonusDto createPaycheckBonusDto) {
        LOG.info("updatePaycheckBonusById");

        try {
            if(!bonusValidatorBean.isPaycheckBonusDtoValid(createPaycheckBonusDto))
                throw new InvalidPaycheckBonusException();

            PaycheckBonus existingPaycheckBonus = entityManager.find(PaycheckBonus.class, paycheckBonusId);
            existingPaycheckBonus.setBonusDescription(createPaycheckBonusDto.getBonusDescription());
            existingPaycheckBonus.setValue(createPaycheckBonusDto.getValue());

            Paycheck oldPaycheck = existingPaycheckBonus.getPaycheck();
            oldPaycheck.getPaycheckBonuses().remove(existingPaycheckBonus);

            Paycheck newPaycheck = entityManager.find(Paycheck.class, createPaycheckBonusDto.getPaycheckId());
            newPaycheck.getPaycheckBonuses().add(existingPaycheckBonus);

            existingPaycheckBonus.setPaycheck(newPaycheck);

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public void deletePaycheckBonusById(Long paycheckBonusId) {
        LOG.info("deletePaycheckBonusById");

        try {
            PaycheckBonus paycheckBonusToDelete = entityManager.find(PaycheckBonus.class, paycheckBonusId);

            entityManager.remove(paycheckBonusToDelete);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public List<PaycheckBonusDto> getAllPaycheckBonusesByPaycheckId(Long paycheckId) {
        LOG.info("getAllPaycheckBonusesByPaycheckId");

        try {
             List<PaycheckBonus> paycheckBonuses = entityManager.createQuery("SELECT pb FROM PaycheckBonus pb WHERE pb.paycheck.id = :paycheckId", PaycheckBonus.class)
                     .setParameter("paycheckId", paycheckId)
                     .getResultList();

             return copyPaycheckBonusesToDto(paycheckBonuses);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private PaycheckBonusDto copyPaycheckBonusToDto(PaycheckBonus paycheckBonus) {
        return new PaycheckBonusDto(paycheckBonus.getPaycheck().getId(),
                paycheckBonus.getValue(),
                paycheckBonus.getBonusDescription(),
                paycheckBonus.getId());
    }

    public List<PaycheckBonusDto> copyPaycheckBonusesToDto(List<PaycheckBonus> paycheckBonuses)
    {
        List<PaycheckBonusDto> paycheckBonusDtos = new ArrayList<>();
        for (PaycheckBonus pb : paycheckBonuses)
        {
              paycheckBonusDtos.add(new PaycheckBonusDto(pb.getPaycheck().getId(), pb.getValue(), pb.getBonusDescription(), pb.getId()));
        }
        return paycheckBonusDtos;
    }
}
