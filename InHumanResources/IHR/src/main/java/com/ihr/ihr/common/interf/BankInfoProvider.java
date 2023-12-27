package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.dtos.CreateBankInfoDto;

public interface BankInfoProvider {
    BankInfoDto getById(Long bankInfoId);

    Long addBankInfo(CreateBankInfoDto createBankInfoDto);

    void updateBankInfo(Long bankInfoId, BankInfoDto bankInfoDto);

    void deleteById(Long bankInfoId);

}
