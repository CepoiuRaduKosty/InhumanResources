package com.ihr.ihr.common.dtos.PaycheckDtos;

public class PaycheckWithNamesDto{
    PaycheckDto paycheckDto;
    String name;

    public PaycheckWithNamesDto(PaycheckDto paycheckDto, String name) {
        this.paycheckDto = paycheckDto;
        this.name = name;
    }

    public PaycheckDto getPaycheckDto() {
        return paycheckDto;
    }

    public void setPaycheckDto(PaycheckDto paycheckDto) {
        this.paycheckDto = paycheckDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
