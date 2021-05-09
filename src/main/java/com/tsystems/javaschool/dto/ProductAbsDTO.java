package com.tsystems.javaschool.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductAbsDTO {
    private int id;
    private String article;
    private String name;
    private String description;
    private String photoLink;
    private int price;
    private String composition;
    private Set<ColourDTO> colours;

    private Set<SizeDTO> sizes;

}
