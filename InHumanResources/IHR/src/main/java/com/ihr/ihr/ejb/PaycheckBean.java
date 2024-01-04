package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.CreatePaycheckDto;
import com.ihr.ihr.common.dtos.EmployeeDtos.EmployeeDto;
import com.ihr.ihr.common.dtos.PaycheckDto;
import com.ihr.ihr.common.excep.UnknownPaycheckException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;
import com.ihr.ihr.common.interf.PaycheckProvider;
import com.ihr.ihr.entities.Employee;
import com.ihr.ihr.entities.Paycheck;
import com.ihr.ihr.entities.PaymentInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class PaycheckBean implements PaycheckProvider {

    private static final Logger LOG = Logger.getLogger(PaycheckBean.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PaycheckDto getPaycheckById(Long paycheckId) {
        try {
            LOG.info("getPaycheckById");

            Paycheck paycheck = findPaycheckById(paycheckId);

            return copyPaycheckToDto(paycheck);
        }
        catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public void updatePaycheckById(Long paycheckId, CreatePaycheckDto updatePaycheckDto) {
        try{
            LOG.info("updatePaycheckById");

            Paycheck paycheck = findPaycheckById(paycheckId);
            PaymentInfo newPaymentInfo = findPaymentInfoById(updatePaycheckDto.getPaymentId());

            setPaycheckInformation(paycheck, updatePaycheckDto);

            PaymentInfo oldPaymentInfo = paycheck.getPaymentInfo();
            oldPaymentInfo.getPaychecks().remove(paycheck);

            newPaymentInfo.getPaychecks().add(paycheck);

            paycheck.setPaymentInfo(newPaymentInfo);
        }
        catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public void createPaycheck(CreatePaycheckDto createPaycheckDto) {
        try{
            LOG.info("createPaycheck");
            PaymentInfo paymentInfo = findPaymentInfoById(createPaycheckDto.getPaymentId());

            Paycheck paycheck = new Paycheck();
            setPaycheckInformation(paycheck, createPaycheckDto);

            paymentInfo.getPaychecks().add(paycheck);

            paycheck.setPaymentInfo(paymentInfo);

            entityManager.persist(paycheck);
        }
        catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public void deletePaycheckById(Long paycheckId) {
        try{
            LOG.info("deletePaycheckById");
            Paycheck paycheck = findPaycheckById(paycheckId);

            PaymentInfo paymentInfo = paycheck.getPaymentInfo();
            paymentInfo.getPaychecks().remove(paycheck);

            entityManager.remove(paycheck);
        }
        catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public List<PaycheckDto> getAllPaychecks() {
        try{
            LOG.info("getAllPaychecks");
            TypedQuery<Paycheck> typedQuery = entityManager.createQuery("SELECT p FROM Paycheck p", Paycheck.class);
            List<Paycheck> paychecks = typedQuery.getResultList();
            return copyPaychecksToDto(paychecks);
        }
        catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public List<PaycheckDto> getAllPaychecksByPaymentInfoId(Long paymentInfoId) {
        try {
            LOG.info("getAllPaychecksByPaymentInfoId");
            TypedQuery<Paycheck> typedQuery = entityManager.createQuery("SELECT p FROM Paycheck p WHERE p.paymentInfo.id = :id", Paycheck.class);
            typedQuery.setParameter("id", paymentInfoId);

            List<Paycheck> paychecks = typedQuery.getResultList();

            return copyPaychecksToDto(paychecks);
        }
        catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public List<PaycheckDto> getAllPaychecksByMonth(Integer month) {
        try{
            LOG.info("getAllPaychecksByMonth");
            TypedQuery<Paycheck> typedQuery = entityManager.createQuery("SELECT p FROM Paycheck p WHERE FUNCTION('MONTH', p.date) = :month", Paycheck.class);
            typedQuery.setParameter("month", month);

            List<Paycheck> paychecks = typedQuery.getResultList();

            return copyPaychecksToDto(paychecks);
        }
        catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public EmployeeDto getEmployeeByPaycheck(Long paycheckId) throws UnknownPaycheckException {
        LOG.info("getEmployeeByPaycheck");

        Paycheck paycheck = findPaycheckById(paycheckId);

        PaymentInfo paymentInfo =  paycheck.getPaymentInfo();

        Employee employee = paymentInfo.getEmployee();

        return new EmployeeDto(employee.getId(),
                employee.getName(),
                employee.getSurname(),
                employee.getGender(),
                employee.getDateOfBirth(),
                employee.getAddress(),
                employee.getReligion(),
                employee.getHoursPerWeek());
    }

    private void setPaycheckInformation(Paycheck paycheck, CreatePaycheckDto paycheckDto) {
        paycheck.setDate(paycheckDto.getDate());
        paycheck.setBasicSalary(paycheckDto.getBasicSalary());
        paycheck.setBonusForSuccess(paycheckDto.getBonusForSuccess());
        paycheck.setNumberOfShares(paycheckDto.getNumberOfShares());
        paycheck.setCumulatedShares(paycheckDto.getCumulatedShares());
        paycheck.setSalaryBeforeTaxes(paycheckDto.getSalaryBeforeTaxes());
        paycheck.setTax(paycheckDto.getTax());
        paycheck.setSocialCharge(paycheckDto.getSocialCharge());
        paycheck.setFinalSalary(paycheckDto.getFinalSalary());
    }

    private PaymentInfo findPaymentInfoById(Long paymentInfoId) throws UnknownPaymentInfoException {
        PaymentInfo paymentInfo = entityManager.find(PaymentInfo.class, paymentInfoId);
        if(paymentInfo == null) {
            throw new UnknownPaymentInfoException("PaymentInfo with ID " + paymentInfoId + " not found");
        }
        return paymentInfo;
    }

    private Paycheck findPaycheckById(Long paycheckId) throws UnknownPaycheckException {
        Paycheck paycheck = entityManager.find(Paycheck.class, paycheckId);
        if(paycheck == null) {
            throw new UnknownPaycheckException("Paycheck with ID " + paycheckId + " not found");
        }
        return paycheck;
    }

    private List<PaycheckDto> copyPaychecksToDto(List<Paycheck> paychecks) {
        List<PaycheckDto> paycheckDtos = new ArrayList<>();
        for(Paycheck paycheck : paychecks) {
            paycheckDtos.add(new PaycheckDto(paycheck.getId(),
                    paycheck.getPaymentInfo().getId(),
                    paycheck.getDate(),
                    paycheck.getBasicSalary(),
                    paycheck.getBonusForSuccess(),
                    paycheck.getNumberOfShares(),
                    paycheck.getCumulatedShares(),
                    paycheck.getSalaryBeforeTaxes(),
                    paycheck.getTax(),
                    paycheck.getSocialCharge(),
                    paycheck.getFinalSalary()));
        }
        return paycheckDtos;
    }

    private PaycheckDto copyPaycheckToDto(Paycheck paycheck) {
        return new PaycheckDto(paycheck.getId(),
                paycheck.getPaymentInfo().getId(),
                paycheck.getDate(),
                paycheck.getBasicSalary(),
                paycheck.getBonusForSuccess(),
                paycheck.getNumberOfShares(),
                paycheck.getCumulatedShares(),
                paycheck.getSalaryBeforeTaxes(),
                paycheck.getTax(),
                paycheck.getSocialCharge(),
                paycheck.getFinalSalary());
    }
}
