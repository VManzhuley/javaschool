package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.dao.PhotoDAO;
import com.tsystems.javaschool.dto.ColourDTO;
import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Photo;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.service.ColourService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Log4j2
public class ColourServiceImpl implements ColourService {
    private final PhotoDAO photoDAO;
    private final ParametersDAO parametersDAO;

    public ColourServiceImpl(PhotoDAO photoDAO, ParametersDAO parametersDAO) {
        this.photoDAO = photoDAO;
        this.parametersDAO = parametersDAO;
    }

    @Override
    public ColourDTO getColourByIdProduct(long id) {
        return mapToColourDTO(photoDAO.getPhotoLink(id));
    }

    @Override
    public void createPhotoLink(ColourDTO colourDTO, long idProductAbs) {
        Photo photo = new Photo();
        ProductAbs productAbs = new ProductAbs();
        productAbs.setId(idProductAbs);
        Colour colourMain = parametersDAO.getColourByName(colourDTO.getColourMain());

        if (!colourDTO.getColourSec().isEmpty()) {
            Colour colourSec = parametersDAO.getColourByName(colourDTO.getColourSec());
            photo.setColourSec(colourSec);
        }

        photo.setProductAbs(productAbs);
        photo.setColourMain(colourMain);
        if (colourDTO.getPhotoLink() == null) {
            photo.setPhotoLink("");
        } else {
            photo.setPhotoLink(colourDTO.getPhotoLink());
        }

        log.info("New photo link for abstract product: {} added to base with colours: {}/{}", idProductAbs, colourDTO.getColourMain(), colourDTO.getColourSec());
        photoDAO.create(photo);
    }

    @Override
    public ColourDTO mapToColourDTO(Photo photo) {
        ColourDTO colourDTO = new ColourDTO();

        colourDTO.setIdColourMain(photo.getColourMain().getId());
        colourDTO.setIdPhoto(photo.getId());
        colourDTO.setPhotoLink(photo.getPhotoLink());
        colourDTO.setColourMain(photo.getColourMain().getName());

        if (photo.getColourSec() != null) {
            colourDTO.setName(photo.getColourMain().getName() + "/" + photo.getColourSec().getName());
            colourDTO.setArticle(String.format("%02d", photo.getColourMain().getId()) +
                    String.format("%02d", photo.getColourSec().getId()));
            colourDTO.setIdColourSec(photo.getColourSec().getId());
            colourDTO.setColourSec(photo.getColourSec().getName());
        } else {
            colourDTO.setName(photo.getColourMain().getName());
            colourDTO.setArticle(String.format("%04d", photo.getColourMain().getId()));
            colourDTO.setColourSec("");
        }

        return colourDTO;
    }

    @Override
    public List<ColourDTO> getAllByProductAbs(long idProductAbs) {
        return photoDAO.getAllByProductAbs(idProductAbs).stream().map(this::mapToColourDTO).collect(Collectors.toList());
    }

    @Override
    public void updatePhoto(ColourDTO colourDTO) {
        Photo photo = photoDAO.getById(colourDTO.getIdPhoto());
        photo.setPhotoLink(colourDTO.getPhotoLink());

        log.info("Photo link: {} updated in base",colourDTO.getIdPhoto());
        photoDAO.update(photo);

    }


}
