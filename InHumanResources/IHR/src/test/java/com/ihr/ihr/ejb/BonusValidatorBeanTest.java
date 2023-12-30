package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.dtos.BonusDto;
import com.ihr.ihr.common.interf.BonusValidation;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import com.ihr.ihr.common.interf.BankInfoValidation;


import static org.junit.jupiter.api.Assertions.*;

public class BonusValidatorBeanTest {
    private final BonusValidation tempBonusValidation = new BonusValidatorBean();
    @Inject
    BonusValidation bonusValidation;

    @Test
    void isValueValid_positive() {
        int value = 5;
        boolean isValid = tempBonusValidation.isValueValid(value);
        assertTrue(isValid);
    }

    @Test
    void isValueValid_negative() {
        int value = 0;
        boolean isValid = tempBonusValidation.isValueValid(value);
        assertFalse(isValid);
    }

    @Test
    void isDenumireBonusValid_positive() {
        String denumireBonus = "Bonus";
        boolean isValid = tempBonusValidation.isDenumireBonusValid(denumireBonus);
        assertTrue(isValid);
    }

    @Test
    void isDenumireBonusValid_negative() {
        String denumireBonus = "";
        boolean isValid = tempBonusValidation.isDenumireBonusValid(denumireBonus);
        assertFalse(isValid);
    }

    @Test
    void isBonusDtoValid_positive() {
        BonusDto validBonusDto = new BonusDto(1L, 2L, 300, "Bonus");
        boolean isValid = tempBonusValidation.isBonusDtoValid(validBonusDto);
        assertTrue(isValid);
    }

    @Test
    void isBonusDtoValid_negative() {
        BonusDto validBonusDto = new BonusDto(1L, 2L, 0, "Bonus");
        boolean isValid = tempBonusValidation.isBonusDtoValid(validBonusDto);
        assertFalse(isValid);
    }

}
