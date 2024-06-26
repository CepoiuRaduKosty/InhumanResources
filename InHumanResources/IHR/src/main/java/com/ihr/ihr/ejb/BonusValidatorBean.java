package com.ihr.ihr.ejb;

import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.CreatePaycheckBonusDto;
import com.ihr.ihr.common.interf.BonusValidation;
import jakarta.ejb.Stateless;

import java.util.logging.Logger;

@Stateless
public class BonusValidatorBean implements BonusValidation {

    private static final Logger LOG = Logger.getLogger(BonusValidatorBean.class.getName());
    @Override
    public boolean isValueValid(Double value) {
        LOG.info("ValueValidator");
        return value > 0;
    }

    @Override
    public boolean isDenumireBonusValid(String denumireBonus) {
        LOG.info("DenumireBonusValidator");
        return !denumireBonus.isEmpty();
    }

    @Override
    public boolean isBonusDtoValid(UpdateBonusDto bonusDto) {
        LOG.info("BonusValidator");
        return isValueValid(bonusDto.getValue()) && isDenumireBonusValid(bonusDto.getBonusDescription());
    }

    @Override
    public boolean isPaycheckBonusDtoValid(CreatePaycheckBonusDto createPaycheckBonusDto) {
        LOG.info("PaycheckBonusValidator");
        return isValueValid(createPaycheckBonusDto.getValue()) && isDenumireBonusValid(createPaycheckBonusDto.getBonusDescription());
    }
}
