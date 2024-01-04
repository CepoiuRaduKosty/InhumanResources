package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.InvalidPaymentInfoException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import com.ihr.ihr.common.interf.PaymentInfoValidation;
import com.ihr.ihr.entities.Paycheck;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.logging.Logger;

@Stateless
@LocalBean
public class PaymentInfoBean implements PaymentInfoProvider {
    private static final Logger LOG = Logger.getLogger(PaymentInfoBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    PaymentInfoValidation paymentInfoValidation;

    @Override
    public PaymentInfoDto findPaymentInfoById(Long paymentInfoId) {
        LOG.info("findPaymentInfoById");
        TypedQuery<PaymentInfo> typedQuery = entityManager.createQuery("SELECT p FROM PaymentInfo p WHERE p.id = :id", PaymentInfo.class);
        typedQuery.setParameter("id", paymentInfoId);

        PaymentInfo paymentInfo = typedQuery.getSingleResult();

        if (paymentInfo == null)
            return null;

        return new PaymentInfoDto(paymentInfo.getId(),
                paymentInfo.getMonthlyBasicSalary(),
                paymentInfo.getSalaryLevel(),
                paymentInfo.getBonusForSuccess(),
                paymentInfo.getNumberOfShares(),
                paymentInfo.getCumulatedShares());
    }

    @Override
    public Long addPaymentInfo(CreatePaymentInfoDto createPaymentInfoDto) throws InvalidPaymentInfoException {
        LOG.info("addPaymentInfo");
        PaymentInfoDto extractedPaymentInfoDto = new PaymentInfoDto(createPaymentInfoDto);
        if (!paymentInfoValidation.isPaymentInfoDtoValid(extractedPaymentInfoDto))
            throw new InvalidPaymentInfoException("Invalid payment info dto");

        PaymentInfo paymentInfo = new PaymentInfo();

        paymentInfo.setMonthlyBasicSalary(createPaymentInfoDto.getMonthlyBasicSalary());
        paymentInfo.setSalaryLevel(createPaymentInfoDto.getSalaryLevel());
        paymentInfo.setBonusForSuccess(createPaymentInfoDto.getBonusForSuccess());
        paymentInfo.setNumberOfShares(createPaymentInfoDto.getNumberOfShares());
        paymentInfo.setCumulatedShares(0);

        entityManager.persist(paymentInfo);

        return paymentInfo.getId();
    }

    @Override
    public void updatePaymentInfo(Long paymentInfoId, PaymentInfoDto paymentInfoDto) throws InvalidPaymentInfoException, UnknownPaymentInfoException {
        LOG.info("updatePaymentInfo");
        if (!paymentInfoValidation.isPaymentInfoDtoValid(paymentInfoDto))
            throw new InvalidPaymentInfoException("Invalid payment info dto");

        PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);

        if (paymentInfo == null)
            throw new UnknownPaymentInfoException();

        paymentInfo.setMonthlyBasicSalary(paymentInfoDto.getMonthlyBasicSalary());
        paymentInfo.setSalaryLevel(paymentInfoDto.getSalaryLevel());
        paymentInfo.setBonusForSuccess(paymentInfoDto.getBonusForSuccess());
        paymentInfo.setNumberOfShares(paymentInfoDto.getNumberOfShares());
        paymentInfo.setCumulatedShares(paymentInfoDto.getCumulatedShares());
    }

    @Override
    public void deletePaymentInfo(Long paymentInfoId) throws UnknownPaymentInfoException {
        PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);

        if (paymentInfo == null)
            throw new UnknownPaymentInfoException();

        entityManager.remove(paymentInfo);
    }

    @Override
    public void incrementCumulatedSharesByNumberOfShares(Long paymentInfoId) throws UnknownPaymentInfoException {
        LOG.info("incrementCumulatedSharesByNumberOfShares");
        PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);
        if (paymentInfo == null)
            throw new UnknownPaymentInfoException();
        Integer currentCumulatedShares = paymentInfo.getCumulatedShares();
        Integer numberOfMonthlyShares = paymentInfo.getNumberOfShares();
        paymentInfo.setCumulatedShares(currentCumulatedShares + numberOfMonthlyShares);
    }

    @Override
    public void resetCumulatedShares(Long paymentInfoId) throws UnknownPaymentInfoException {
        LOG.info("resetCumulatedShares");
        PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);
        if (paymentInfo == null)
            throw new UnknownPaymentInfoException();
        paymentInfo.setCumulatedShares(0);
    }
}
