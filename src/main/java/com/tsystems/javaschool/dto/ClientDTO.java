package com.tsystems.javaschool.dto;

import lombok.Data;

@Data
public class ClientDTO {
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String zip;
    private String country;
    private String city;
    private String street;
    private String building;
    private String apartment;

    //return true if address is empty
    public boolean addressIsEmpty() {
        return ((this.zip + this.country + this.city + this.street + this.building + this.apartment).length() == 0);
    }
}
