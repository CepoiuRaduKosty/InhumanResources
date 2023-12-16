package com.ihr.ihr.common.dtos;

public class BonusDto {
    Long id;
    Long id_payment;
    int value;
    String denumire_bonus;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_payment() {
        return this.id_payment;
    }

    public void setId_payment(Long id_payment) {
        this.id_payment = id_payment;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDenumire_bonus() {
        return this.denumire_bonus;
    }

    public void setDenumire_bonus(String denumire_bonus) {
        this.denumire_bonus = denumire_bonus;
    }
}
