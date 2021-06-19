package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.dao.WeightVolumeDAO;
import com.tsystems.javaschool.dto.SizeDTO;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.entity.product.Size;
import com.tsystems.javaschool.entity.product.WeightVolume;
import com.tsystems.javaschool.service.SizeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j2
public class SizeServiceImpl implements SizeService {
    private final WeightVolumeDAO weightVolumeDAO;
    private final ParametersDAO parametersDAO;

    public SizeServiceImpl(WeightVolumeDAO weightVolumeDAO, ParametersDAO parametersDAO) {
        this.weightVolumeDAO = weightVolumeDAO;
        this.parametersDAO = parametersDAO;
    }

    @Override
    public SizeDTO getSize(long idProduct) {
        return mapToSizeDTO(weightVolumeDAO.getWVByIdProduct(idProduct));
    }

    @Override
    public void createWeightVolume(SizeDTO sizeDTO, long idProductAbs) {
        WeightVolume weightVolume = new WeightVolume();
        ProductAbs productAbs = new ProductAbs();
        productAbs.setId(idProductAbs);
        Size size = parametersDAO.getSizeByName(sizeDTO.getSize());

        weightVolume.setSize(size);
        weightVolume.setProductAbs(productAbs);

        log.info("New weight and volume for abstract product: {} added to base with size: {}", idProductAbs, sizeDTO.getSize());
        weightVolumeDAO.create(weightVolume);
    }

    @Override
    public SizeDTO mapToSizeDTO(WeightVolume weightVolume) {
        SizeDTO sizeDTO = new SizeDTO();

        sizeDTO.setIdSize(weightVolume.getSize().getId());
        sizeDTO.setSize(weightVolume.getSize().getName());
        sizeDTO.setIdWV(weightVolume.getId());
        sizeDTO.setWeight(weightVolume.getWeight());
        sizeDTO.setVolume(weightVolume.getVolume());

        return sizeDTO;
    }

    @Override
    public List<SizeDTO> getAllByProductAbs(long idProductAbs) {
        return weightVolumeDAO.getAllByProductAbs(idProductAbs).stream().map(this::mapToSizeDTO).collect(Collectors.toList());
    }

    @Override
    public void updateWeightVolume(SizeDTO sizeDTO) {
        WeightVolume weightVolume = weightVolumeDAO.getById(sizeDTO.getIdWV());
        weightVolume.setVolume(sizeDTO.getVolume());
        weightVolume.setWeight(sizeDTO.getWeight());

        log.info("Weight and volume: {} updated in base", sizeDTO.getIdSize());
        weightVolumeDAO.update(weightVolume);
    }
}
