package com.tsystems.javaschool.dto;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Data
public class ProductAbsDTO {
    private int id;

    @Size(min = 3, message = "Article too short")
    private String article;

    @Size(min = 5, message = "Name too short")
    private String name;

    private String description;
    private String photoLink;
    @Min(value = 1, message = "Price too low")
    @Max(value = 50000, message = "I hope so, but no")
    private int price;
    private String composition;
    @NotEmpty(message = "Please add at least one colour")
    private List<ColourDTO> colours = new LinkedList<>();
    @NotEmpty(message = "Please add at least one size")
    private List<SizeDTO> sizes = new LinkedList<>();
    private int idCategory;
    private int idDescription;
    private int idComposition;
    private List<ProductDTO> products;

    private ColourDTO findColoursByNames(String colourMain, String colourSec) {
        for (ColourDTO colour : this.colours
        ) {
            if ((colour.getColourMain().equals(colourMain)) && (colour.getColourSec().equals(colourSec))) return colour;
        }
        return null;
    }

    public void addColour(ColourDTO colourDTO) {
        ColourDTO colour = this.findColoursByNames(colourDTO.getColourMain(), colourDTO.getColourSec());

        if ((colour == null) && (colourDTO.getColourMain()!=null) && (!colourDTO.getColourMain().isEmpty())) {
            colour = new ColourDTO();
            colour.setColourMain(colourDTO.getColourMain());
            colour.setColourSec(colourDTO.getColourSec());
            this.colours.add(colour);
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

    public void addProducts() {
        this.products = new LinkedList<>();
        for (ColourDTO colourDTO : this.colours
        ) {
            for (SizeDTO sizeDTO : this.sizes
            ) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setColour(colourDTO);
                productDTO.setSize(sizeDTO);
                products.add(productDTO);
            }
        }

    }
    public void clearSizesAndColours(){
        this.sizes=new LinkedList<>();
        this.colours=new LinkedList<>();
    }
}
