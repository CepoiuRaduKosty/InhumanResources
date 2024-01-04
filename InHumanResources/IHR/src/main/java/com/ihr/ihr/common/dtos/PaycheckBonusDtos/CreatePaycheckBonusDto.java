package com.ihr.ihr.common.dtos.PaycheckBonusDtos;

public class CreatePaycheckBonusDto {
    Long paycheckId;
    Double value;
    String bonusDescription;

    public CreatePaycheckBonusDto(Long paycheckId, Double value, String bonusDescription) {
        this.paycheckId = paycheckId;
        this.value = value;
        this.bonusDescription = bonusDescription;
    }

    public Long getPaycheckId() {
        return paycheckId;
    }

    public void setPaycheckId(Long paycheckId) {
        this.paycheckId = paycheckId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getBonusDescription() {
        return bonusDescription;
    }

    public void setBonusDescription(String bonusDescription) {
        this.bonusDescription = bonusDescription;
    }
}
