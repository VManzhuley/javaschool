package com.tsystems.javaschool.service;


import com.tsystems.javaschool.dto.ColourDTO;
import com.tsystems.javaschool.entity.product.Photo;

import java.util.List;

public interface ColourService {

    ColourDTO getColourByIdProduct(long id);

    void createPhotoLink(ColourDTO colourDTO, long idProductAbs);

    ColourDTO mapToColourDTO(Photo photo);

    List<ColourDTO> getAllByProductAbs(long idProductAbs);

    void updatePhoto(ColourDTO colourDTO);

}
