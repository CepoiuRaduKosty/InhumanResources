package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.BankInfoDtos.CreateBankInfoDto;
import com.ihr.ihr.common.dtos.BankInfoDtos.UpdateBankInfoDto;

public interface BankInfoProvider {
    BankInfoDto getById(Long bankInfoId);

    Long addBankInfo(CreateBankInfoDto createBankInfoDto);

    void updateBankInfo(Long bankInfoId, UpdateBankInfoDto updateBankInfoDto);

    void deleteById(Long bankInfoId);

}
