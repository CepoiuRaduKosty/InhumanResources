package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.PaycheckBonusDtos.CreatePaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.PaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckDtos.PaycheckDto;
import com.ihr.ihr.common.interf.PayckeckBonusProvider;
import com.ihr.ihr.entities.Paycheck;
import com.ihr.ihr.entities.PaycheckBonus;
import jakarta.ejb.EJBException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PaycheckBonusBean implements PayckeckBonusProvider {
    private static final Logger LOG = Logger.getLogger(PaycheckBean.class.getName());

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
    public Long createPaycheckBonus(CreatePaycheckBonusDto createPaycheckBonusDto) {
        LOG.info("createPaycheckBonus");


        PaycheckBonus paycheckBonus = new PaycheckBonus();

        paycheckBonus.setBonusDescription(createPaycheckBonusDto.getBonusDescription());
        paycheckBonus.setPaycheckId(createPaycheckBonusDto.getPaycheckId());
        paycheckBonus.setValue(createPaycheckBonusDto.getValue());

        entityManager.persist(paycheckBonus);

        return paycheckBonus.getId();
    }

    @Override
    public void updatePaycheckBonusById(Long paycheckBonusId, CreatePaycheckBonusDto createPaycheckBonusDto) {
        LOG.info("updatePaycheckBonusById");

        try {
            PaycheckBonus existingPaycheckBonus = entityManager.find(PaycheckBonus.class, paycheckBonusId);

            existingPaycheckBonus.setValue(createPaycheckBonusDto.getValue());
            existingPaycheckBonus.setPaycheckId(createPaycheckBonusDto.getPaycheckId());
            existingPaycheckBonus.setBonusDescription(createPaycheckBonusDto.getBonusDescription());

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
             List<PaycheckBonus> paycheckBonuses = entityManager.createQuery("SELECT pb FROM PaycheckBonus pb WHERE pb.paycheckId = :paycheckId", PaycheckBonus.class)
                     .setParameter("paycheckId", paycheckId)
                     .getResultList();

             return copyPaycheckBonusesToDto(paycheckBonuses);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private PaycheckBonusDto copyPaycheckBonusToDto(PaycheckBonus paycheckBonus) {
        return new PaycheckBonusDto(paycheckBonus.getPaycheckId(),
                paycheckBonus.getValue(),
                paycheckBonus.getBonusDescription(),
                paycheckBonus.getId());
    }

    public List<PaycheckBonusDto> copyPaycheckBonusesToDto(List<PaycheckBonus> paycheckBonuses)
    {
        List<PaycheckBonusDto> paycheckBonusDtos = new ArrayList<>();
        for (PaycheckBonus pb : paycheckBonuses)
        {
              paycheckBonusDtos.add(new PaycheckBonusDto(pb.getPaycheckId(), pb.getValue(), pb.getBonusDescription(), pb.getId()));
        }
        return paycheckBonusDtos;
    }
}
