package com.ihr.ihr.common.dtos;

public class BonusDto {
    Long id;
    Long idPayment;
    Integer value;
    String bonusDescription;

    public Long getId() {
        return this.id;
    }

    public BonusDto(Long id, Long idPayment, int value, String bonusDescription) {
        this.id = id;
        this.idPayment = idPayment;
        this.value = value;
        this.bonusDescription = bonusDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPayment() {
        return this.idPayment;
    }

    public void setIdPayment(Long idPayment) {
        this.idPayment = idPayment;
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getBonusDescription() {
        return this.bonusDescription;
    }

    public void setBonusDescription(String bonusDescription) {
        this.bonusDescription = bonusDescription;
    }
}
