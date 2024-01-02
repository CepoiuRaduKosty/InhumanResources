package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.dtos.CreateBankInfoDto;
import com.ihr.ihr.common.dtos.EmployeeDto;
import com.ihr.ihr.common.excep.InvalidBankInfoException;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.common.interf.BankInfoValidation;
import com.ihr.ihr.entities.BankInfo;
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
public class BankInfoBean implements BankInfoProvider {
    private static final Logger LOG = Logger.getLogger(BankInfoBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @Inject
    BankInfoValidation bankInfoValidation;

    @Override
    public BankInfoDto getById(Long bankInfoId) {
        LOG.info("getBankInfoById");

        try {
            TypedQuery<BankInfo> typedQuery = entityManager.createQuery("SELECT b FROM BankInfo b WHERE b.id = :id", BankInfo.class);
            typedQuery.setParameter("id", bankInfoId);
            BankInfo bankInfo = typedQuery.getSingleResult();

            return new BankInfoDto(bankInfo.getId(), bankInfo.getIBAN(), bankInfo.getBankName());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public Long addBankInfo(CreateBankInfoDto createBankInfoDto) throws InvalidBankInfoException {
        LOG.info("AddBankInfo");

        if(!bankInfoValidation.isBankInfoDtoValid(createBankInfoDto))
            throw new InvalidBankInfoException();

        BankInfo bankInfo = new BankInfo();

        bankInfo.setIBAN(createBankInfoDto.getIBAN());
        bankInfo.setBankName(createBankInfoDto.getBankName());

        entityManager.persist(bankInfo);

        return bankInfo.getId();
    }

    @Override
    public void updateBankInfo(Long bankInfoId, CreateBankInfoDto createBankInfoDto) throws InvalidBankInfoException {
        LOG.info("updateBankInfo");

        if(!bankInfoValidation.isBankInfoDtoValid(createBankInfoDto))
            throw new InvalidBankInfoException();

        BankInfo bankInfo = entityManager.find(BankInfo.class, bankInfoId);

        bankInfo.setBankName(createBankInfoDto.getBankName());
        bankInfo.setIBAN(createBankInfoDto.getIBAN());
    }

    @Override
    public void deleteById(Long bankInfoId) {
        LOG.info("deleteBankInfoById");

        BankInfo bankInfo = entityManager.find(BankInfo.class, bankInfoId);
        entityManager.remove(bankInfo);
    }

}
