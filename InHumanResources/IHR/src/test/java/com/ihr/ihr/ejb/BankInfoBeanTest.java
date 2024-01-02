package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.dtos.CreateBankInfoDto;
import com.ihr.ihr.common.excep.InvalidBankInfoException;
import com.ihr.ihr.common.interf.BankInfoProvider;
import com.ihr.ihr.entities.BankInfo;
import jakarta.ejb.EJBException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankInfoBeanTest {

    @Mock
    EntityManager entityManager;

    @InjectMocks
    BankInfoBean bankInfoBean;
    @Test
    void getById_positive_idExists() {
        BankInfoDto sampleBankInfoDto = new BankInfoDto(1L, "SampleIBAN", "SampleBank");

        TypedQuery<BankInfo> typedQuery = Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT b FROM BankInfo b WHERE b.id = :id", BankInfo.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("id", 1L)).thenReturn(typedQuery);

        BankInfo tempBankInfo = new BankInfo();
        tempBankInfo.setId(sampleBankInfoDto.getId());
        tempBankInfo.setBankName(sampleBankInfoDto.getBankName());
        tempBankInfo.setIBAN(sampleBankInfoDto.getIBAN());
        when(typedQuery.getSingleResult()).thenReturn(tempBankInfo);

        BankInfoDto result = bankInfoBean.getById(1L);

        assertEquals(sampleBankInfoDto.getId(), result.getId());
        assertEquals(sampleBankInfoDto.getIBAN(), result.getIBAN());
        assertEquals(sampleBankInfoDto.getBankName(), result.getBankName());
    }

    @Test
    void getById_negative_idDoesNotExist() {
        TypedQuery<BankInfo> typedQuery = Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT b FROM BankInfo b WHERE b.id = :id", BankInfo.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter(eq("id"), anyLong())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenThrow(NoResultException.class);
        
        assertThrows(EJBException.class, () -> bankInfoBean.getById(999L));
    }

    @Test
    void addBankInfo_positive() throws InvalidBankInfoException {
        CreateBankInfoDto sampleBankInfoDto = new CreateBankInfoDto("SampleIBAN", "SampleBank");

        bankInfoBean.addBankInfo(sampleBankInfoDto);

        verify(entityManager).persist(any(BankInfo.class));

        ArgumentCaptor<BankInfo> captor = ArgumentCaptor.forClass(BankInfo.class);
        verify(entityManager).persist(captor.capture());

        BankInfo capturedBankInfo = captor.getValue();
        assertNotNull(capturedBankInfo);
        assertEquals(sampleBankInfoDto.getIBAN(), capturedBankInfo.getIBAN());
        assertEquals(sampleBankInfoDto.getBankName(), capturedBankInfo.getBankName());
    }

    @Test
    void addBankInfo_negative_null() {
        assertThrows(NullPointerException.class, () -> bankInfoBean.addBankInfo(null));
    }

    @Test
    void updateBankInfo_positive() throws InvalidBankInfoException {
        CreateBankInfoDto updatedBankInfoDto = new CreateBankInfoDto("UpdatedIBAN", "UpdatedBank");
        Long updatedBankInfoId = 1L;

        BankInfo existingBankInfo = new BankInfo();
        existingBankInfo.setId(1L);
        existingBankInfo.setIBAN("InitialIBAN");
        existingBankInfo.setBankName("InitialBank");

        when(entityManager.find(BankInfo.class, updatedBankInfoId)).thenReturn(existingBankInfo);

        bankInfoBean.updateBankInfo(updatedBankInfoId, updatedBankInfoDto);

        verify(entityManager).find(BankInfo.class, updatedBankInfoId);

        assertEquals(updatedBankInfoDto.getIBAN(), existingBankInfo.getIBAN());
        assertEquals(updatedBankInfoDto.getBankName(), existingBankInfo.getBankName());
    }

    @Test
    void updateBankInfo_negative_idNotFound() {
        CreateBankInfoDto nonExistingBankInfoDto = new CreateBankInfoDto("UpdatedIBAN", "UpdatedBank");

        Long Id = 999L;

        when(entityManager.find(BankInfo.class, Id)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> bankInfoBean.updateBankInfo(Id, nonExistingBankInfoDto));
    }

    @Test
    void deleteById_positive() {
        Long bankInfoIdToDelete = 1L;

        BankInfo existingBankInfo = new BankInfo();
        existingBankInfo.setId(bankInfoIdToDelete);

        when(entityManager.find(BankInfo.class, bankInfoIdToDelete)).thenReturn(existingBankInfo);

        bankInfoBean.deleteById(bankInfoIdToDelete);

        verify(entityManager).remove(existingBankInfo);
    }

    @Test
    void deleteById_negative_idNotFound(){
        Long nonExistingBankInfoId = 999L;

        when(entityManager.find(BankInfo.class, nonExistingBankInfoId)).thenReturn(null);

        bankInfoBean.deleteById(nonExistingBankInfoId);

        ArgumentCaptor<BankInfo> captor = ArgumentCaptor.forClass(BankInfo.class);
        verify(entityManager).remove(captor.capture());

        BankInfo capturedBankInfo = captor.getValue();
        assertNull(capturedBankInfo);
    }
}