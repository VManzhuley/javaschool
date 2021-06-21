package com.tsystems.javaschool.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColourDTO {
    private String article;
    private String name;

    private long idColourMain;
    private long idColourSec;
    private String colourMain;
    private String colourSec;

    private long idPhoto;
    private String photoLink;
}

