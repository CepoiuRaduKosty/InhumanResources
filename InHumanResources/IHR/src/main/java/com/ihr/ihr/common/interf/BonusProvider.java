package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BonusDtos.BonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.CreateBonusDto;
import com.ihr.ihr.common.dtos.BonusDtos.UpdateBonusDto;
import com.ihr.ihr.common.excep.UnknownBonusException;
import com.ihr.ihr.common.excep.UnknownPaymentInfoException;

import java.util.List;

public interface BonusProvider {
    BonusDto getBonusDtoById(Long bonusId) throws UnknownBonusException;
    void updateBonusById(Long bonusId, UpdateBonusDto updateBonusDto) throws UnknownBonusException;
    void createBonusByDto(CreateBonusDto createBonusDto) throws UnknownPaymentInfoException;
    void removeBonusById(Long bonusId) throws UnknownBonusException;
    List<BonusDto> getAllBonuses();
    List<BonusDto> getAllBonusesByPaymentId(Long paymentId);
}
