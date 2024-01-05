package com.ihr.ihr.common.dtos.PaycheckBonusDtos;

public class PaycheckBonusDto extends CreatePaycheckBonusDto {
    Long id;

    public PaycheckBonusDto(Long paycheckId, Double value, String bonusDescription) {
        super(paycheckId, value, bonusDescription);
    }

    public PaycheckBonusDto(Long paycheckId, Double value, String bonusDescription, Long id) {
        super(paycheckId, value, bonusDescription);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
