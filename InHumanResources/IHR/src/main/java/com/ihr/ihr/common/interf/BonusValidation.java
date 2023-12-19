package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BonusDto;

public interface BonusValidation {
    boolean isValueValid(Integer value);
    boolean isDenumireBonusValid(String denumireBonus);
    boolean isBonusDtoValid(BonusDto bonusDto);
}
