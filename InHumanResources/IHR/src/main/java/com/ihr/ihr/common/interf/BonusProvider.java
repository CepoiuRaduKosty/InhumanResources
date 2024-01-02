package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.CreateBonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;

public interface BonusProvider {
    BonusDto getBonusDtoById(Long bonusId) throws UnknownBonusException;
    void updateBonusById(Long bonusId, UpdateBonusDto updateBonusDto) throws UnknownBonusException;
    void createBonusByDto(CreateBonusDto createBonusDto) throws UnknownPaymentInfoException;
}
