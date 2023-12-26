package com.ihr.ihr.entities;

import jakarta.persistence.*;

@Entity
public class BankInfo {
    @Id
    @GeneratedValue
    private Long Id;
    private String IBAN;
    private String bankName;
    @OneToOne
    private Employee employee;

    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }

    public String getIBAN() {
        return IBAN;
    }
    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
