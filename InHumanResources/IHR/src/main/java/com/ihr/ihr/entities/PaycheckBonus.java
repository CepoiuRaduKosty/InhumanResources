package com.ihr.ihr.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class PaycheckBonus {
    @Id
    @GeneratedValue
    private Long id;

    private Double value;

    private String bonusDescription;

    @ManyToOne
    private Paycheck paycheck;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Paycheck getPaycheck() {
        return paycheck;
    }

    public void setPaycheck(Paycheck paycheck) {
        this.paycheck = paycheck;
    }
}