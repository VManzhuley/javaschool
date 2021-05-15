package com.tsystems.javaschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShippingType {
    SELF ("Self pick-up"),
    SPB ("In St. Petersburg"),
    ANOTHER("To another city");

    private String title;

}
