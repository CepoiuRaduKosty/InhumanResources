package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.PaycheckBonusDtos.CreatePaycheckBonusDto;
import com.ihr.ihr.common.dtos.PaycheckBonusDtos.PaycheckBonusDto;
import com.ihr.ihr.common.excep.InvalidPaycheckBonusException;

import java.util.List;

public interface PaycheckBonusProvider {
    PaycheckBonusDto getPaycheckBonusDtoById(Long id);
    Long createPaycheckBonus(CreatePaycheckBonusDto createPaycheckBonusDto) throws InvalidPaycheckBonusException;
    void updatePaycheckBonusById(Long paycheckBonusId, CreatePaycheckBonusDto createPaycheckBonusDto);
    void deletePaycheckBonusById(Long paycheckBonusId);
    List<PaycheckBonusDto> getAllPaycheckBonusesByPaycheckId(Long paycheckId);
}
