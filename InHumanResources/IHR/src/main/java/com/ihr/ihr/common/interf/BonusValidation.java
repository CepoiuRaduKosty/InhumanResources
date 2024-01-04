package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;

public interface BonusValidation {
    boolean isValueValid(Double value);
    boolean isDenumireBonusValid(String denumireBonus);
    boolean isBonusDtoValid(UpdateBonusDto bonusDto);
}
