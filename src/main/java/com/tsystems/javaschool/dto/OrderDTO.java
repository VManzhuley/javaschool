package com.tsystems.javaschool.dto;

import com.tsystems.javaschool.entity.PaymentType;
import com.tsystems.javaschool.entity.ShippingType;
import com.tsystems.javaschool.entity.Status;
import lombok.Data;

import java.util.List;


@Data
public class OrderDTO {
    private int id;
    private ClientDTO client;
    private String date;
    private PaymentType payment;
    private ShippingType shipping;
    private Status status;
    private long amountTotal;
    private List<ProductOrderedDTO> productOrderedList;
}

