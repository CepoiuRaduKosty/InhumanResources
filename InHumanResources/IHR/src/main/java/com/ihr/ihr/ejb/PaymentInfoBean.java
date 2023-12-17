package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.PaymentInfoDto;
import com.ihr.ihr.common.excep.NonPositiveIncomeException;
import com.ihr.ihr.common.interf.PaymentInfoProvider;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.logging.Logger;

@Stateless
public class PaymentInfoBean implements PaymentInfoProvider {
    private static final Logger LOG = Logger.getLogger(PaymentInfoBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public PaymentInfoDto findPaymentInfoById(Integer paymentInfoId) {
        try{
            LOG.info("findPaymentInfoById");
            TypedQuery<PaymentInfo> typedQuery = entityManager.createQuery("SELECT p FROM PaymentInfo p WHERE p.id = :id", PaymentInfo.class);
            typedQuery.setParameter("id", paymentInfoId);

            PaymentInfo paymentInfo = typedQuery.getSingleResult();

            return new PaymentInfoDto(paymentInfo.getId(),
                    paymentInfo.getMonthlyBasicSalary(),
                    paymentInfo.getSalaryLevel(),
                    paymentInfo.getBonusForSuccess(),
                    paymentInfo.getNumberOfShares());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public void addPaymentInfo(PaymentInfoDto paymentinfoDto) throws NonPositiveIncomeException {
        try{
            LOG.info("addPaymentInfo");
            PaymentInfo paymentInfo = new PaymentInfo();

            paymentInfo.setId(paymentinfoDto.getId());
            paymentInfo.setMonthlyBasicSalary(paymentinfoDto.getMonthlyBasicSalary());
            paymentInfo.setSalaryLevel(paymentinfoDto.getSalaryLevel());
            paymentInfo.setBonusForSuccess(paymentinfoDto.getBonusForSuccess());
            paymentInfo.setNumberOfShares(paymentinfoDto.getNumberOfShares());

            entityManager.persist(paymentInfo);
        }
        catch (Exception ex){
            throw new EJBException(ex);
        }
    }

    @Override
    public void updatePaymentInfo(Integer paymentInfoId, PaymentInfoDto paymentInfoDto) throws NonPositiveIncomeException {
        try{
            LOG.info("updatePaymentInfo");
            PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);

            paymentInfo.setMonthlyBasicSalary(paymentInfoDto.getMonthlyBasicSalary());
            paymentInfo.setSalaryLevel(paymentInfoDto.getSalaryLevel());
            paymentInfo.setBonusForSuccess(paymentInfoDto.getBonusForSuccess());
            paymentInfo.setNumberOfShares(paymentInfoDto.getNumberOfShares());
        }
        catch (Exception ex){
            throw new EJBException(ex);
        }
    }

    @Override
    public void deletePaymentInfo(Integer paymentInfoId) {
        try{
            LOG.info("deletePaymentInfo");
            PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);
            entityManager.remove(paymentInfo);
        }
        catch (Exception ex){
            throw new EJBException(ex);
        }
    }
}
