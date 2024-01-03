package com.ihr.ihr.common.dtos.BonusDtos;

public class BonusDto extends CreateBonusDto{
    Long id;

    public BonusDto(Long id, Long idPayment, Integer value, String bonusDescription) {
        super(value, bonusDescription, idPayment);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
