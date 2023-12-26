package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDto;

public interface BankInfoProvider {
    BankInfoDto getById(Long bankInfoId);

    void addBankInfo(BankInfoDto bankinfoDto);

    void updateBankInfo(Long bankInfoId, BankInfoDto bankInfoDto);

    void deleteById(Long bankInfoId);

}
