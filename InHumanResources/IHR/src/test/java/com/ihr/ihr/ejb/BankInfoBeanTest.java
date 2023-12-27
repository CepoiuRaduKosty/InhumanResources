package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.entities.BankInfo;
import jakarta.ejb.EJBException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class BankInfoBeanTest {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    BankInfoBean bankInfoBean;
    @Test
    void getById_positive_idExists() {
        BankInfoDto sampleBankInfoDto = new BankInfoDto(1, "SampleIBAN", "SampleBank");

        TypedQuery<BankInfo> typedQuery = Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT b FROM BankInfo b WHERE b.id = :id", BankInfo.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("id", 1)).thenReturn(typedQuery);

        BankInfo tempBankInfo = new BankInfo();
        tempBankInfo.setId(sampleBankInfoDto.getId());
        tempBankInfo.setBankName(sampleBankInfoDto.getBankName());
        tempBankInfo.setIBAN(sampleBankInfoDto.getIBAN());
        when(typedQuery.getSingleResult()).thenReturn(tempBankInfo);

        // Invoke the method
        BankInfoDto result = bankInfoBean.getById(1);

        // Assertions
        assertEquals(sampleBankInfoDto.getId(), result.getId());
        assertEquals(sampleBankInfoDto.getIBAN(), result.getIBAN());
        assertEquals(sampleBankInfoDto.getBankName(), result.getBankName());
    }

    @Test
    void getById_negative_idDoesNotExist() {
        TypedQuery<BankInfo> typedQuery = Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT b FROM BankInfo b WHERE b.id = :id", BankInfo.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("id"), anyInt())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(NoResultException.class);

        // Testing if the method throws an exception when BankInfo is not found
        assertThrows(EJBException.class, () -> bankInfoBean.getById(999));
    }

    @Test
    void addBankInfo() {
    }

    @Test
    void updateBankInfo() {
    }

    @Test
    void deleteById() {
    }
}