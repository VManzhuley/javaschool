package com.tsystems.javaschool.dto;

import com.tsystems.javaschool.entity.PaymentType;
import com.tsystems.javaschool.entity.ShippingType;
import com.tsystems.javaschool.entity.Status;
import lombok.Data;

import java.util.List;


@Data
public class OrderDTO {
    private long id;
    private ClientDTO client;
    private String date;
    private PaymentType payment;
    private ShippingType shipping;
    private Status status;
    private double amount;
    private List<ProductOrderedDTO> productOrderedList;
}

