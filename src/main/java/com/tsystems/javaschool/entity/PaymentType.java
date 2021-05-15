package com.tsystems.javaschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PaymentType {
    BANK_TRANSFER ("Bank Transfer"),
    CREDIT_CARD ("Credit Card"),
    CASH ("Cash upon receive");

    private String title;
    }
