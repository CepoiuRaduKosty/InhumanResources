package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.interf.BonusValidation;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BonusValidatorBeanTest {
    @InjectMocks
    BonusValidatorBean bonusValidatorBean;

    @Test
    void isValueValid_positive() {
        Double value = 5.0;
        boolean isValid = bonusValidatorBean.isValueValid(value);
        assertTrue(isValid);
    }

    @Test
    void isValueValid_negative() {
        Double value = 0.0;
        boolean isValid = bonusValidatorBean.isValueValid(value);
        assertFalse(isValid);
    }

    @Test
    void isDenumireBonusValid_positive() {
        String denumireBonus = "Bonus";
        boolean isValid = bonusValidatorBean.isDenumireBonusValid(denumireBonus);
        assertTrue(isValid);
    }

    @Test
    void isDenumireBonusValid_negative() {
        String denumireBonus = "";
        boolean isValid = bonusValidatorBean.isDenumireBonusValid(denumireBonus);
        assertFalse(isValid);
    }

    @Test
    void isBonusDtoValid_positive() {
        BonusDto validBonusDto = new BonusDto(1L, 2L, 300.0, "Bonus");
        boolean isValid = bonusValidatorBean.isBonusDtoValid(validBonusDto);
        assertTrue(isValid);
    }

    @Test
    void isBonusDtoValid_negative() {
        BonusDto validBonusDto = new BonusDto(1L, 2L, 0.0, "Bonus");
        boolean isValid = bonusValidatorBean.isBonusDtoValid(validBonusDto);
        assertFalse(isValid);
    }

}
