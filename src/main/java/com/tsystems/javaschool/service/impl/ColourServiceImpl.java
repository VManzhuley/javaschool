package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.PhotoDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.ColourDTO;
import com.tsystems.javaschool.entity.product.Photo;
import com.tsystems.javaschool.service.ColourService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ColourServiceImpl implements ColourService {
    private final ProductDAO productDAO;
    private final PhotoDAO photoDAO;

    public ColourServiceImpl(ProductDAO productDAO, PhotoDAO photoDAO) {
        this.productDAO = productDAO;
        this.photoDAO = photoDAO;

    }

    @Override
    public ColourDTO getColour(int idProduct) {
        ColourDTO colourDTO = new ColourDTO();
        Photo photo = photoDAO.getPhotoLink(idProduct);

        colourDTO.setIdColourMain(photo.getColourMain().getId());
        colourDTO.setPhotoLink(photo.getPhotoLink());

        if (photo.getColourSec() != null) {
            colourDTO.setName(photo.getColourMain().getName() + "/" + photo.getColourSec().getName());
            colourDTO.setArticle(String.format("%02d",photo.getColourMain().getId())+
                    String.format("%02d",photo.getColourSec().getId()));
            colourDTO.setIdColourSec(photo.getColourMain().getId());
        } else {
            colourDTO.setName(photo.getColourMain().getName());
            colourDTO.setArticle(String.format("%04d",photo.getColourMain().getId()));
        }

        return colourDTO;
    }


}
