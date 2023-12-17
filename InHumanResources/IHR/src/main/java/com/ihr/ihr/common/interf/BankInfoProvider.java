package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDto;

public interface BankInfoProvider {
    BankInfoDto getById(Integer bankInfoId);

    void addBankInfo(BankInfoDto bankinfoDto);

    void updateBankInfo(Integer bankInfoId, BankInfoDto bankInfoDto);

    void deleteById(Integer bankInfoId);

}
