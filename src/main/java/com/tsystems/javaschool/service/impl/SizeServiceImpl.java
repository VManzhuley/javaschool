package com.tsystems.javaschool.service.impl;

import com.tsystems.javaschool.dao.WeightVolumeDAO;
import com.tsystems.javaschool.dto.SizeDTO;
import com.tsystems.javaschool.entity.product.WeightVolume;
import com.tsystems.javaschool.service.SizeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SizeServiceImpl implements SizeService {
    private final WeightVolumeDAO weightVolumeDAO;

    public SizeServiceImpl(WeightVolumeDAO weightVolumeDAO) {
        this.weightVolumeDAO = weightVolumeDAO;
    }

    @Override
    public SizeDTO getSize(int idProduct) {
        SizeDTO sizeDTO=new SizeDTO();
        WeightVolume weightVolume=weightVolumeDAO.getWVByIdProduct(idProduct);
        sizeDTO.setName(weightVolume.getSize().getName());
        sizeDTO.setVolume(weightVolume.getVolume());
        sizeDTO.setWeight(weightVolume.getWeight());
        return sizeDTO;
    }
}
