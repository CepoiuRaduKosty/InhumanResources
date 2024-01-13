package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.BonusEntryDto;
import com.ihr.ihr.common.dtos.BonusDtos.CreateBonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.excep.InvalidBonusException;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.interf.BonusProvider;
import com.ihr.ihr.common.interf.BonusValidation;
import com.ihr.ihr.entities.BonusInfo;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class BonusesBean implements BonusProvider {
    private static final Logger LOG = Logger.getLogger(BonusesBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    BonusValidation bonusValidation;

    private BonusDto getBonusDtoFromBonusInfo(BonusInfo bonusInfo) {
        return new BonusDto(
                bonusInfo.getId(),
                bonusInfo.getPaymentInfo().getId(),
                bonusInfo.getValue(),
                bonusInfo.getBonusDescription()
        );
    }

    private List<BonusDto> getBonusDtoListFromBonusInfoList(List<BonusInfo> bonusInfos) {
        List<BonusDto> bonusDtos = new ArrayList<>();
        for (BonusInfo bonusInfo : bonusInfos) {
            bonusDtos.add(getBonusDtoFromBonusInfo(bonusInfo));
        }
        return bonusDtos;
    }

    private void updateBonusInfoFromUpdateBonusDto(BonusInfo destinationBonusInfo, UpdateBonusDto updateBonusDto) throws InvalidBonusException {
        if(!bonusValidation.isBonusDtoValid(updateBonusDto))
            throw new InvalidBonusException();

        destinationBonusInfo.setValue(updateBonusDto.getValue());
        destinationBonusInfo.setBonusDescription(updateBonusDto.getBonusDescription());
    }

    private void linkBonusInfoWithPaymentInfo(BonusInfo bonusInfo, Long paymentInfoId) throws UnknownPaymentInfoException {
        PaymentInfo paymentInfo = safeGetPaymentInfoById(paymentInfoId);
        bonusInfo.setPaymentInfo(paymentInfo);
        paymentInfo.getBonuses().add(bonusInfo);
    }

    private BonusInfo safeGetBonusInfoById(Long bonusId) throws UnknownBonusException {
        BonusInfo foundBonus = entityManager.find(BonusInfo.class, bonusId);
        if (foundBonus == null) throw new UnknownBonusException();
        return foundBonus;
    }

    private PaymentInfo safeGetPaymentInfoById(Long paymentId) throws UnknownPaymentInfoException {
        PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentId);
        if (paymentInfo == null) throw new UnknownPaymentInfoException();
        return paymentInfo;
    }

    @Override
    public List<BonusDto> getBonusDtoByPaymentId(Long paymentId) throws UnknownPaymentInfoException {
        LOG.info("getBonusDtoByPaymentId");
        PaymentInfo paymentInfo = safeGetPaymentInfoById(paymentId);
        List<BonusInfo> bonuses = paymentInfo.getBonuses();
        return getBonusDtoListFromBonusInfoList(bonuses);
    }

    @Override
    public BonusDto getBonusDtoById(Long bonusId) throws UnknownBonusException {
        LOG.info("getBonusDtoById");
        return getBonusDtoFromBonusInfo(safeGetBonusInfoById(bonusId));
    }

    @Override
    public void updateBonusById(Long bonusId, UpdateBonusDto updateBonusDto) throws UnknownBonusException, InvalidBonusException {
        LOG.info("updateBonusById");
        updateBonusInfoFromUpdateBonusDto(safeGetBonusInfoById(bonusId), updateBonusDto);
    }

    @Override
    public void createBonusByDto(CreateBonusDto createBonusDto) throws UnknownPaymentInfoException, InvalidBonusException {
        LOG.info("createBonusByDto");

        if(!bonusValidation.isBonusDtoValid(createBonusDto))
            throw new InvalidBonusException();

        BonusInfo newBonusInfo = new BonusInfo();
        updateBonusInfoFromUpdateBonusDto(newBonusInfo, createBonusDto);
        linkBonusInfoWithPaymentInfo(newBonusInfo, createBonusDto.getIdPayment());

        entityManager.persist(newBonusInfo);
    }

    @Override
    public void removeBonusById(Long bonusId) throws UnknownBonusException {
        LOG.info("removeBonusById");
        BonusInfo bonusToDelete = safeGetBonusInfoById(bonusId);
        PaymentInfo paymentInfo = bonusToDelete.getPaymentInfo();
        paymentInfo.getBonuses().remove(bonusToDelete);
        entityManager.remove(bonusToDelete);
    }

    @Override
    public List<BonusDto> getAllBonuses() {
        LOG.info("getAllBonuses");
        try {
            TypedQuery<BonusInfo> typedQuery = entityManager.createQuery("SELECT C FROM BonusInfo c", BonusInfo.class);
            List<BonusInfo> bonuses = typedQuery.getResultList();
            return getBonusDtoListFromBonusInfoList(bonuses);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public List<BonusDto> getAllBonusesByPaymentId(Long paymentId) {
        LOG.info("getAllBonusesByPaymentId");
        try {
            TypedQuery<BonusInfo> typedQuery = entityManager.createQuery("SELECT b FROM BonusInfo b WHERE b.paymentInfo.id = :paymentId", BonusInfo.class).setParameter("paymentId", paymentId);
            List<BonusInfo> bonuses = typedQuery.getResultList();
            return getBonusDtoListFromBonusInfoList(bonuses);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public List<BonusEntryDto> searchBonusesByEmployeeName(String employeeName) {
        LOG.info("searchBonusesByEmployeeName");
        TypedQuery<BonusInfo> typedQuery = entityManager.createQuery(
                "SELECT b FROM BonusInfo b JOIN FETCH b.paymentInfo pi JOIN FETCH pi.employee e " +
                        "WHERE LOWER(e.name) LIKE :employeeName OR LOWER(e.surname) LIKE :employeeName",
                BonusInfo.class
        );
        typedQuery.setParameter("employeeName", "%" + employeeName.toLowerCase() + "%");

        return copyBonusInfoToBonusEntry(typedQuery);
    }

    private static List<BonusEntryDto> copyBonusInfoToBonusEntry(TypedQuery<BonusInfo> typedQuery) {
        List<BonusInfo> bonusInfos = typedQuery.getResultList();
        List<BonusEntryDto> bonusEntryDtos = new ArrayList<>();

        for (BonusInfo bonusInfo : bonusInfos) {
            BonusEntryDto bonusEntryDto = new BonusEntryDto(
                    bonusInfo.getId(),
                    bonusInfo.getPaymentInfo().getEmployee().getName(),
                    bonusInfo.getPaymentInfo().getEmployee().getSurname(),
                    bonusInfo.getBonusDescription(),
                    bonusInfo.getValue()
            );
            bonusEntryDtos.add(bonusEntryDto);
        }
        return bonusEntryDtos;
    }

}
