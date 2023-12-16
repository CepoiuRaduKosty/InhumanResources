package com.ihr.ihr.common.dtos;

public class BonusDto {
    Long id;
    Long idPayment;
    int value;
    String denumireBonus;

    public Long getId() {
        return this.id;
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

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDenumireBonus() {
        return this.denumireBonus;
    }

    public void setDenumireBonus(String denumireBonus) {
        this.denumireBonus = denumireBonus;
    }
}
