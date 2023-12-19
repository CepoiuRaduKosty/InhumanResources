package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BonusDto;

public interface BonusValidation {
    boolean ValueValidator(Integer value);
    boolean DenumireBonusValidator(String denumireBonus);
    boolean BonusValidator(BonusDto bonusDto);
}
