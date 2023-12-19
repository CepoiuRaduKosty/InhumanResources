package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDto;
import com.ihr.ihr.common.interf.BonusValidation;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;

@Stateless
public class BonusValidatorBean implements BonusValidation {

    private static final Logger LOG = Logger.getLogger(BonusValidatorBean.class.getName());
    @Override
    public boolean ValueValidator(Integer value) {
        LOG.info("ValueValidator");
        return value > 0;
    }

    @Override
    public boolean DenumireBonusValidator(String denumireBonus) {
        LOG.info("DenumireBonusValidator");
        return !denumireBonus.isEmpty();
    }

    @Override
    public boolean BonusValidator(BonusDto bonusDto) {
        LOG.info("BonusValidator");
        return ValueValidator(bonusDto.getValue()) && DenumireBonusValidator(bonusDto.getDenumireBonus());
    }
}
