package com.ihr.ihr.common.interf;

import com.ihr.ihr.common.dtos.BankInfoDto;
import jakarta.ejb.Local;

@Local
public interface BankInfoProvider {
    BankInfoDto getById(int id_bankinfo);   // returns BankInfoDto of bank info with given id or null if none found
    void add(BankInfoDto bankinfoDto); // tries to create new bank info based on BankInfoDto ; automatically assigns id (id given in dto is ignored)

    void update(int id_bankinfo, BankInfoDto bankInfoDto); // tries to (fully) replace bank info data at id_bankinfo with data given in DTO

    void deleteById(int id_bankinfo); // tries to remove bank info with given id

}
