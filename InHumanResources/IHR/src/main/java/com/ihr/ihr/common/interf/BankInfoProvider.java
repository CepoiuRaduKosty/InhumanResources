package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDto;

public interface BankInfoProvider {
    BankInfoDto getById(int id_bankinfo);

    void addBankInfo(BankInfoDto bankinfoDto);

    void updateBankInfo(int id_bankinfo, BankInfoDto bankInfoDto);

    void deleteById(int id_bankinfo);

}
