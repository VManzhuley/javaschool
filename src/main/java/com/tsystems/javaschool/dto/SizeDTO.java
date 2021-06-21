package com.tsystems.javaschool.dto;

import lombok.Data;

@Data
public class SizeDTO {
    private long idSize;
    private String size;

    private long idWV;
    private double weight;
    private double volume;
}
