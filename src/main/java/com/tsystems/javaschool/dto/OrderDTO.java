package com.tsystems.javaschool.dto;

import lombok.Data;

import java.util.List;


@Data
public class OrderDTO {
    private int id;
    private ClientDTO client;
    private String date;
    private String payment;
    private String shipping;
    private String status;
    private long amountTotal;
    private List<ProductOrderedDTO> productOrderedList;
}
enum Status{

}
enum Shipping{

}
enum Payment{

}

