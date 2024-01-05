package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.CreatePaycheckBonusDto;

public interface BonusValidation {
    boolean isValueValid(Double value);
    boolean isDenumireBonusValid(String denumireBonus);
    boolean isBonusDtoValid(UpdateBonusDto bonusDto);
    boolean isPaycheckBonusDtoValid(CreatePaycheckBonusDto createPaycheckBonusDto);
}
