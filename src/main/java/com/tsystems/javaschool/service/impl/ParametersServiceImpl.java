package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.entity.product.Colour;
import com.tsystems.javaschool.entity.product.Composition;
import com.tsystems.javaschool.entity.product.Description;
import com.tsystems.javaschool.entity.product.Size;
import com.tsystems.javaschool.service.ParametersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Log4j2
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

    @Override
    public void createDescription(String name) {
        if (!name.isEmpty()) {
            Description description = new Description();
            description.setName(name);

            log.info("New description: {} added to base", name);
            parametersDAO.createDescription(description);
        }
    }

    @Override
    public void createComposition(String name) {
        if (!name.isEmpty()) {
            Composition composition = new Composition();
            composition.setName(name);

            log.info("New composition: {} added to base", name);
            parametersDAO.createComposition(composition);
        }
    }

    @Override
    public List<Composition> gelAllCompositionByCategory(long id) {
        return parametersDAO.getAllCompositionByCategory(id);
    }

    @Override
    public List<Description> getAllDescriptionByCategory(long id) {
        return parametersDAO.getAllDescriptionByCategory(id);
    }


}
