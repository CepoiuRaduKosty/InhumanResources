package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.entities.BankInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.logging.Logger;

@Stateless
public class BankInfoBean implements BankInfoProvider {
    private static final Logger LOG = Logger.getLogger(BankInfoBean.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public BankInfoDto getById(Long bankInfoId) {
        LOG.info("getBankInfoById");

        try {
            TypedQuery<BankInfo> typedQuery = entityManager.createQuery("SELECT b FROM BankInfo b WHERE b.Id = :id", BankInfo.class);
            typedQuery.setParameter("id", bankInfoId);
            BankInfo bankInfo = typedQuery.getSingleResult();

            return new BankInfoDto(bankInfo.getId(), bankInfo.getIBAN(), bankInfo.getBankName(), bankInfo.getEmployee().getId());
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    @Override
    public void addBankInfo(BankInfoDto bankinfoDto) {
        LOG.info("AddBankInfo");

        BankInfo bankInfo = new BankInfo();

        bankInfo.setId(bankinfoDto.getId());
        bankInfo.setIBAN(bankinfoDto.getIBAN());
        bankInfo.setBankName(bankinfoDto.getBankName());

        entityManager.persist(bankInfo);
    }

    @Override
    public void updateBankInfo(Long bankInfoId, BankInfoDto bankInfoDto) {
        LOG.info("updateBankInfo");

        BankInfo bankInfo = entityManager.find(BankInfo.class, bankInfoId);

        bankInfo.setBankName(bankInfoDto.getBankName());
        bankInfo.setIBAN(bankInfoDto.getIBAN());
    }

    @Override
    public void deleteById(Long bankInfoId) {
        LOG.info("deleteBankInfoById");

        BankInfo bankInfo = entityManager.find(BankInfo.class, bankInfoId);
        entityManager.remove(bankInfo);
    }
}
