package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.CreatePaymentInfoDto;
import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import com.ihr.ihr.common.interf.PaymentInfoValidation;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ValidationException;

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
        try {
            LOG.info("findPaymentInfoById");
            TypedQuery<PaymentInfo> typedQuery = entityManager.createQuery("SELECT p FROM PaymentInfo p WHERE p.id = :id", PaymentInfo.class);
            typedQuery.setParameter("id", paymentInfoId);

            PaymentInfo paymentInfo = typedQuery.getSingleResult();

            return new PaymentInfoDto(paymentInfo.getId(),
                    paymentInfo.getMonthlyBasicSalary(),
                    paymentInfo.getSalaryLevel(),
                    paymentInfo.getBonusForSuccess(),
                    paymentInfo.getNumberOfShares(),
                    paymentInfo.getCummulatedShares());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public Long addPaymentInfo(CreatePaymentInfoDto createPaymentInfoDto) throws NonPositiveIncomeException, ValidationException {
        LOG.info("addPaymentInfo");
        PaymentInfoDto extractedPaymentInfoDto = new PaymentInfoDto(createPaymentInfoDto);
        if(!paymentInfoValidation.isPaymentInfoDtoValid(extractedPaymentInfoDto))
            throw new ValidationException("Wrong PaymentInfoDto");

        try {

            PaymentInfo paymentInfo = new PaymentInfo();

            paymentInfo.setMonthlyBasicSalary(createPaymentInfoDto.getMonthlyBasicSalary());
            paymentInfo.setSalaryLevel(createPaymentInfoDto.getSalaryLevel());
            paymentInfo.setBonusForSuccess(createPaymentInfoDto.getBonusForSuccess());
            paymentInfo.setNumberOfShares(createPaymentInfoDto.getNumberOfShares());
            paymentInfo.setCummulatedShares(0);

            entityManager.persist(paymentInfo);

            return paymentInfo.getId();
        }
        catch (Exception ex){
            throw new EJBException(ex);
        }
    }

    @Override
    public void updatePaymentInfo(Long paymentInfoId, PaymentInfoDto paymentInfoDto) throws NonPositiveIncomeException, ValidationException {
        LOG.info("updatePaymentInfo");
        if(!paymentInfoValidation.isPaymentInfoDtoValid(paymentInfoDto))
            throw new ValidationException("Wrong PaymentInfoDto");

        try {
            PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);

            paymentInfo.setMonthlyBasicSalary(paymentInfoDto.getMonthlyBasicSalary());
            paymentInfo.setSalaryLevel(paymentInfoDto.getSalaryLevel());
            paymentInfo.setBonusForSuccess(paymentInfoDto.getBonusForSuccess());
            paymentInfo.setNumberOfShares(paymentInfoDto.getNumberOfShares());
            paymentInfo.setCummulatedShares(paymentInfoDto.getCummulatedShares());
        } catch (NullPointerException ex) {
            throw new NullPointerException(ex.getMessage());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public void deletePaymentInfo(Long paymentInfoId) {
        try {
            LOG.info("deletePaymentInfo");
            PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);
            entityManager.remove(paymentInfo);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }
}
