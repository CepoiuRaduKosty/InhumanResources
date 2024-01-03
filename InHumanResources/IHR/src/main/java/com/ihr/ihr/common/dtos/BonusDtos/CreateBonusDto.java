package com.ihr.ihr.common.dtos.BonusDtos;

public class CreateBonusDto extends UpdateBonusDto {

    Long idPayment;

    public CreateBonusDto(Integer value, String bonusDescription, Long idPayment) {
        super(value, bonusDescription);
        this.idPayment = idPayment;
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }
}
