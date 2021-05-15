package com.tsystems.javaschool.dto;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ProductAbsDTO {
    private int id;

    @Size(min=3, message = "Article too short")
    private String article;

    @Size(min=5, message = "Name too short")
    private String name;

    private String description;
    private String photoLink;
    @Min(value = 1, message = "Price too low")
    @Max(value = 50000, message = "I hope so, but no")
    private int price;
    private String composition;
    @NotEmpty(message = "Please add at least one colour")
    private Set<ColourDTO> colours=new HashSet<>();
    @NotEmpty(message = "Please add at least one size")
    private Set<SizeDTO> sizes=new HashSet<>();
    private int idCategory;
    private int idDescription;
    private int idComposition;
    private List<ProductDTO> products=new ArrayList<>();

    private ColourDTO findColoursByNames(String colourMain, String colourSec) {
        for (ColourDTO colour : this.colours
        ) {
            if ((colour.getColourMain().equals(colourMain)) & (colour.getColourSec().equals(colourSec))) return colour;
        }
        return null;
    }

    public void addColour(ColourDTO colourDTO) {
        ColourDTO colour = this.findColoursByNames(colourDTO.getColourMain(),colourDTO.getColourSec());

        if ((colour==null)&(!colourDTO.getColourMain().equals(""))){
            colour = new ColourDTO();
            colour.setColourMain(colourDTO.getColourMain());
            colour.setColourSec(colourDTO.getColourSec());
            this.colours.add(colour);

            for (int i=0;i<this.getSizes().size();i++)
            {
                    products.add(new ProductDTO());
            }
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

        if ((size == null) & (!sizeDTO.getSize().equals(""))) {
            size = new SizeDTO();
            size.setSize(sizeDTO.getSize());
            this.sizes.add(size);

            for (int j=0;j<this.getColours().size();j++){
                products.add(new ProductDTO());
            }
        }
    }
}
