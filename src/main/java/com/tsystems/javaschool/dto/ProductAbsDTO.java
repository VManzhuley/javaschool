package com.tsystems.javaschool.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Set<ColourDTO> colours=new HashSet<>();
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
