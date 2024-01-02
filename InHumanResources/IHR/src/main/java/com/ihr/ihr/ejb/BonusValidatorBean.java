package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.interf.BonusValidation;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;

@Stateless
public class BonusValidatorBean implements BonusValidation {

    private static final Logger LOG = Logger.getLogger(BonusValidatorBean.class.getName());
    @Override
    public boolean isValueValid(Integer value) {
        LOG.info("ValueValidator");
        return value > 0;
    }

    @Override
    public boolean isDenumireBonusValid(String denumireBonus) {
        LOG.info("DenumireBonusValidator");
        return !denumireBonus.isEmpty();
    }

    @Override
    public boolean isBonusDtoValid(BonusDto bonusDto) {
        LOG.info("BonusValidator");
        return isValueValid(bonusDto.getValue()) && isDenumireBonusValid(bonusDto.getBonusDescription());
    }
}
