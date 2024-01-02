package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.CreateBonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.interf.BonusProvider;
import com.ihr.ihr.entities.BonusInfo;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.logging.Logger;

@Stateless
public class BonusBean implements BonusProvider {
    private static final Logger LOG = Logger.getLogger(BonusBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    private BonusDto getBonusDtoFromBonusInfo(BonusInfo bonusInfo) {
        return new BonusDto(
                bonusInfo.getId(),
                bonusInfo.getPaymentInfo().getId(),
                bonusInfo.getValue(),
                bonusInfo.getBonusDescription()
        );
    }

    private void updateBonusInfoFromUpdateBonusDto(BonusInfo destinationBonusInfo, UpdateBonusDto updateBonusDto) {
        destinationBonusInfo.setValue(updateBonusDto.getValue());
        destinationBonusInfo.setBonusDescription(updateBonusDto.getBonusDescription());
    }

    private void linkBonusInfoWithPaymentInfo(BonusInfo bonusInfo, Long paymentInfoId) throws UnknownPaymentInfoException {
        PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);
        if (paymentInfo == null) throw new UnknownPaymentInfoException();
        bonusInfo.setPaymentInfo(paymentInfo);
        paymentInfo.getBonuses().add(bonusInfo);
    }


    @Override
    public BonusDto getBonusDtoById(Long bonusId) throws UnknownBonusException {
        BonusInfo foundBonus = entityManager.find(BonusInfo.class, bonusId);
        if (foundBonus == null) throw new UnknownBonusException();
        return getBonusDtoFromBonusInfo(foundBonus);
    }

    @Override
    public void updateBonusById(Long bonusId, UpdateBonusDto updateBonusDto) throws UnknownBonusException {
        BonusInfo foundBonus = entityManager.find(BonusInfo.class, bonusId);
        if (foundBonus == null) throw new UnknownBonusException();
        updateBonusInfoFromUpdateBonusDto(foundBonus, updateBonusDto);
    }

    @Override
    public void createBonusByDto(CreateBonusDto createBonusDto) throws UnknownPaymentInfoException {
        BonusInfo newBonusInfo = new BonusInfo();
        updateBonusInfoFromUpdateBonusDto(newBonusInfo, createBonusDto);
        linkBonusInfoWithPaymentInfo(newBonusInfo, createBonusDto.getIdPayment());
        entityManager.persist(newBonusInfo);
    }
}
