package com.ihr.ihr.common.dtos.BonusDtos;

public class UpdateBonusDto {
    Integer value;
    String bonusDescription;

    public UpdateBonusDto(Integer value, String bonusDescription) {
        this.value = value;
        this.bonusDescription = bonusDescription;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getBonusDescription() {
        return bonusDescription;
    }

    public void setBonusDescription(String bonusDescription) {
        this.bonusDescription = bonusDescription;
    }
}
