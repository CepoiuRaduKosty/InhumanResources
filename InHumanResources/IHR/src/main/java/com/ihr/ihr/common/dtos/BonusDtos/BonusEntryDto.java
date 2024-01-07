package com.ihr.ihr.common.dtos.BonusDtos;

public class BonusEntryDto {
    Long id;
    String employeeName;
    String employeeSurname;
    String bonusDescription;
    Double bonusValue;

    public BonusEntryDto(Long id, String employeeName, String employeeSurname, String bonusDescription, Double bonusValue) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.bonusDescription = bonusDescription;
        this.bonusValue = bonusValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSurname() {
        return employeeSurname;
    }

    public void setEmployeeSurname(String employeeSurname) {
        this.employeeSurname = employeeSurname;
    }

    public String getBonusDescription() {
        return bonusDescription;
    }

    public void setBonusDescription(String bonusDescription) {
        this.bonusDescription = bonusDescription;
    }

    public Double getBonusValue() {
        return bonusValue;
    }

    public void setBonusValue(Double bonusValue) {
        this.bonusValue = bonusValue;
    }
}
