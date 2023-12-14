package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.entities.BankInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BankInfoServiceTest {

    @Mock
    private EntityManager entityManagerMock;

    @InjectMocks
    private BankInfoService bankInfoService;

    @Mock
    private TypedQuery<BankInfo> typedQueryMock;

    @Mock
    private BankInfo bankInfoMock;

    @Test
    void getById() {
        when(entityManagerMock.createQuery("SELECT b FROM BankInfo b WHERE b.id = :id", BankInfo.class))
                .thenReturn(typedQueryMock);
        when(typedQueryMock.getSingleResult())
                .thenReturn(bankInfoMock);
        when(bankInfoMock.getId())
                .thenReturn(33);
        when(bankInfoMock.getIBAN())
                .thenReturn("5550125");
        when(bankInfoMock.getBankName())
                .thenReturn("getBankName");

        BankInfoDto bankInfoDtoResult = bankInfoService.getById(1);

        assertEquals(bankInfoDtoResult.getId(), 33);
        assertEquals(bankInfoDtoResult.getIBAN(), "5550125");
        assertEquals(bankInfoDtoResult.getBankName(), "getBankName");
    }

}