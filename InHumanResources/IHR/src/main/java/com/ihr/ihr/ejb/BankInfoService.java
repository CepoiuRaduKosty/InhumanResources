package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.entities.BankInfo;
import jakarta.ejb.EJBException;
import jakarta.ejb.Local;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.logging.Logger;

@Stateless
@LocalBean
public class BankInfoService implements BankInfoProvider{
    private static final Logger LOG = Logger.getLogger(BankInfoService.class.getName());
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public BankInfoDto getById(int id_bankinfo) {
        LOG.info("getBankInfoById");

        try{
            TypedQuery<BankInfo> typedQuery = entityManager.createQuery("SELECT b FROM BankInfo b WHERE b.id = :id", BankInfo.class);
            typedQuery.setParameter("id", id_bankinfo);
            BankInfo bankInfo = typedQuery.getSingleResult();

            return new BankInfoDto(bankInfo.getId(), bankInfo.getIBAN(), bankInfo.getBankName());
        }
        catch (Exception ex)
        {
            throw new EJBException(ex);
        }
    }

    @Override
    public void add(BankInfoDto bankinfoDto) {
        LOG.info("AddBankInfo");

        BankInfo bankInfo = new BankInfo();

        bankInfo.setId(bankinfoDto.getId());
        bankInfo.setIBAN(bankinfoDto.getIBAN());
        bankInfo.setBankName(bankinfoDto.getBankName());

        entityManager.persist(bankInfo);

    }

    @Override
    public void update(int id_bankinfo, BankInfoDto bankInfoDto) {
        LOG.info("updateBankInfo");

        BankInfo bankInfo = entityManager.find(BankInfo.class, id_bankinfo);

        bankInfo.setBankName(bankInfoDto.getBankName());
        bankInfo.setIBAN(bankInfoDto.getIBAN());
    }

    @Override
    public void deleteById(int id_bankinfo) {
        LOG.info("deleteBankInfoById");

        BankInfo bankInfo = entityManager.find(BankInfo.class, id_bankinfo);
        entityManager.remove(bankInfo);
    }
}
