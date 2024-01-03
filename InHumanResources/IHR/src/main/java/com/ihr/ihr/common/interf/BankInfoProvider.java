package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDtos.BankInfoDto;
import com.ihr.ihr.common.dtos.BankInfoDtos.CreateBankInfoDto;
import com.ihr.ihr.common.dtos.BankInfoDtos.UpdateBankInfoDto;
import com.ihr.ihr.common.excep.InvalidBankInfoException;

public interface BankInfoProvider {
    BankInfoDto getById(Long bankInfoId);

    Long addBankInfo(CreateBankInfoDto createBankInfoDto) throws InvalidBankInfoException;

    void updateBankInfo(Long bankInfoId, UpdateBankInfoDto updateBankInfoDto) throws InvalidBankInfoException;

    void deleteById(Long bankInfoId);

}
