package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.ParametersDAO;
import com.tsystems.javaschool.dao.WeightVolumeDAO;
import com.tsystems.javaschool.dto.SizeDTO;
import com.tsystems.javaschool.entity.product.ProductAbs;
import com.tsystems.javaschool.entity.product.Size;
import com.tsystems.javaschool.entity.product.WeightVolume;
import com.tsystems.javaschool.service.SizeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SizeServiceImpl implements SizeService {
    private final WeightVolumeDAO weightVolumeDAO;
    private final ParametersDAO parametersDAO;

    public SizeServiceImpl(WeightVolumeDAO weightVolumeDAO, ParametersDAO parametersDAO) {
        this.weightVolumeDAO = weightVolumeDAO;
        this.parametersDAO = parametersDAO;
    }

    @Override
    public SizeDTO getSize(int idProduct) {

        WeightVolume weightVolume=weightVolumeDAO.getWVByIdProduct(idProduct);

        return mapToSizeDTO(weightVolume);
    }

    @Override
    public void addWeightVolume(SizeDTO sizeDTO, int idProductAbs) {
        WeightVolume weightVolume = new WeightVolume();
        ProductAbs productAbs = new ProductAbs();
        productAbs.setId(idProductAbs);
        Size size = parametersDAO.getSizeByName(sizeDTO.getSize());

        weightVolume.setVolume(sizeDTO.getVolume());
        weightVolume.setWeight(sizeDTO.getWeight());
        weightVolume.setSize(size);
        weightVolume.setProductAbs(productAbs);

        weightVolumeDAO.add(weightVolume);
    }

    @Override
    public SizeDTO mapToSizeDTO(WeightVolume weightVolume) {
        SizeDTO sizeDTO = new SizeDTO();

        sizeDTO.setSize(weightVolume.getSize().getName());
        sizeDTO.setWeight(weightVolume.getWeight());
        sizeDTO.setVolume(weightVolume.getVolume());

        return sizeDTO;
    }

    @Override
    public List<SizeDTO> allByProductAbs(int idProductAbs) {
        return weightVolumeDAO.getAllByProductAbs(idProductAbs).stream().map(this::mapToSizeDTO).collect(Collectors.toList());
    }
}
