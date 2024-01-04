package com.ihr.ihr.common.dtos.BonusDtos;

public class UpdateBonusDto {
    Double value;
    String bonusDescription;

    public UpdateBonusDto(Double value, String bonusDescription) {
        this.value = value;
        this.bonusDescription = bonusDescription;
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
