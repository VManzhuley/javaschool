package com.tsystems.javaschool.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    NEW ("New"),
    WAITING_FOR_PAYMENT ("Waiting for payment"),
    TRANSPORT_COMPANY ("Transferred to TC"),
    COMPLETED ("Completed"),
    CANCELED ("Canceled");

    private String title;

}
