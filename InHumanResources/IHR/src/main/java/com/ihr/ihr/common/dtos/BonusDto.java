package com.ihr.ihr.common.dtos;

public class BonusDto {
    Long id;
    Long idPayment;
    Integer value;
    String denumireBonus;

    public Long getId() {
        return this.id;
    }

    public BonusDto(Long id, Long idPayment, int value, String denumireBonus) {
        this.id = id;
        this.idPayment = idPayment;
        this.value = value;
        this.denumireBonus = denumireBonus;
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

    public String getDenumireBonus() {
        return this.denumireBonus;
    }

    public void setDenumireBonus(String denumireBonus) {
        this.denumireBonus = denumireBonus;
    }
}
