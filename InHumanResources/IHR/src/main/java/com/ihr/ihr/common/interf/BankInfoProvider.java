package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDto;
import com.ihr.ihr.common.dtos.CreateBankInfoDto;
import com.ihr.ihr.common.excep.InvalidBankInfoException;

public interface BankInfoProvider {
    BankInfoDto getById(Long bankInfoId);

    Long addBankInfo(CreateBankInfoDto createBankInfoDto) throws InvalidBankInfoException;

    void updateBankInfo(Long bankInfoId, CreateBankInfoDto createBankInfoDto) throws InvalidBankInfoException;

    void deleteById(Long bankInfoId);

}
