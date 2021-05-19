package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Composition;
import com.tsystems.javaschool.entity.product.Description;
import com.tsystems.javaschool.entity.product.Size;
import com.tsystems.javaschool.service.ParametersService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametersServiceImpl implements ParametersService {
    private final ParametersDAO parametersDAO;


    public ParametersServiceImpl(ParametersDAO parametersDAO) {
        this.parametersDAO = parametersDAO;

    }

    @Override
    public List<Composition> gelAllComposition() {
        return parametersDAO.gelAllComposition();
    }

    @Override
    public List<Description> getAllDescription() {
        return parametersDAO.getAllDescription();
    }

    @Override
    public List<Size> getAllSize() {
        return parametersDAO.getAllSize();
    }

    @Override
    public List<Colour> getAllColour() {
        return parametersDAO.getAllColour();
    }


}
