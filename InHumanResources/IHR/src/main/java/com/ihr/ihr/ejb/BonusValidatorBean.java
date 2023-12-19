package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDto;
import com.ihr.ihr.common.interf.BonusValidation;
import jakarta.ejb.Stateless;

@Stateless
public class BonusValidatorBean implements BonusValidation {
    @Override
    public boolean ValueValidator(Integer value) {
        return value > 0;
    }

    @Override
    public boolean DenumireBonusValidator(String denumireBonus) {
        return !denumireBonus.isEmpty();
    }

    @Override
    public boolean BonusValidator(BonusDto bonusDto) {
        return ValueValidator(bonusDto.getValue()) && DenumireBonusValidator(bonusDto.getDenumireBonus());
    }
}
