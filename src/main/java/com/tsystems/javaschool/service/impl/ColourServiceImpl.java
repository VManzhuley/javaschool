package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ProductAbsDAO;
import com.tsystems.javaschool.dao.ProductDAO;
import com.tsystems.javaschool.dto.ColourDTO;
import com.tsystems.javaschool.entity.product.Product;
import com.tsystems.javaschool.service.ColourService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ColourServiceImpl implements ColourService {
    private final ProductDAO productDAO;
    private final ProductAbsDAO productAbsDAO;

    public ColourServiceImpl(ProductDAO productDAO, ProductAbsDAO productAbsDAO) {
        this.productDAO = productDAO;
        this.productAbsDAO = productAbsDAO;
    }

    @Override
    public ColourDTO getColour(int idProduct) {
        String code;
        String name;
        Product product = productDAO.getProduct(idProduct);

        if (product.getColourSec() != null) {
            name = product.getColourMain().getName() + "/" + product.getColourSec().getName();
            code= String.format("%02d",product.getColourMain().getId())+
                    String.format("%02d",product.getColourSec().getId());
        } else {
            name = product.getColourMain().getName();
            code=String.format("%04d",product.getColourMain().getId());
        }

        return new ColourDTO(code, name);
    }


}
