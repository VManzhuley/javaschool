package com.tsystems.javaschool.service;


import com.tsystems.javaschool.dto.ColourDTO;
import com.tsystems.javaschool.entity.product.Photo;

import java.util.List;

public interface ColourService {
    ColourDTO getColourByIdProduct(int id);
    void addPhotoLink(ColourDTO colourDTO,int idProductAbs);
    ColourDTO mapToColourDTO(Photo photo);
    List<ColourDTO> allByProductAbs(int idProductAbs);

}
