package com.tsystems.javaschool.dto;

import lombok.Data;


@Data
public class OrderDTO {
    private ClientDTO client;
    private String date;
    private String payment;
    private String shipping;
    private String status;
}
