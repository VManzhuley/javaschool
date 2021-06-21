package com.tsystems.javaschool.dto;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductAbsDTO {
    private long id;

    @Size(min = 3, message = "Article too short")
    private String article;

    @Size(min = 5, message = "Name too short")
    private String name;

    @Min(value = 1, message = "Price too low")
    @Max(value = 50000, message = "I hope so, but no")
    private double price;

    private String description;
    private String photoLink;
    private String composition;
    private boolean outdated;

    private long idCategory;
    private long idDescription;
    private long idComposition;

    private List<ProductDTO> products=new ArrayList<>();
    private List<ColourDTO> colours=new ArrayList<>();
    private List<SizeDTO> sizes = new ArrayList<>();

    private ColourDTO findColoursByNames(String colourMain, String colourSec) {
        for (ColourDTO colour : this.colours
        ) {
            if ((colour.getColourMain().equals(colourMain)) && (colour.getColourSec().equals(colourSec))) return colour;
        }
        return null;
    }

    public void addColour(ColourDTO colourDTO) {
        ColourDTO colour = this.findColoursByNames(colourDTO.getColourMain(), colourDTO.getColourSec());

        if ((colour == null) && (colourDTO.getColourMain() != null) && (!colourDTO.getColourMain().isEmpty())) {
            colour = new ColourDTO();
            colour.setColourMain(colourDTO.getColourMain());
            colour.setColourSec(colourDTO.getColourSec());
            this.colours.add(colour);
        }
    }

    public void deleteColour(int n) {
        if ((n < this.colours.size())&&(this.colours.get(n).getIdPhoto()==0)) {
            this.colours.remove(n);
        }
    }

    private SizeDTO findSizeByName(String name) {
        for (SizeDTO size : this.sizes
        ) {
            if (size.getSize().equals(name)) return size;
        }
        return null;
    }

    public void addSize(SizeDTO sizeDTO) {
        SizeDTO size = this.findSizeByName(sizeDTO.getSize());

        if ((size == null) && (sizeDTO.getSize() != null) && (!sizeDTO.getSize().isEmpty())) {
            size = new SizeDTO();
            size.setSize(sizeDTO.getSize());
            this.sizes.add(size);
        }
    }

    public void deleteSize(int n) {
        if ((n < this.sizes.size())&&(this.sizes.get(n).getIdWV()==0)) {
            this.sizes.remove(n);
        }
    }

    public void addProduct(ProductDTO productDTO) {
        this.products.add(productDTO);
    }

}
