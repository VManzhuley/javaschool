package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.SizeDTO;
import com.tsystems.javaschool.entity.product.WeightVolume;

import java.util.List;

public interface SizeService {

    SizeDTO getSize(long idProduct);

    void createWeightVolume(SizeDTO sizeDTO, long idProductAbs);

    SizeDTO mapToSizeDTO(WeightVolume weightVolume);

    List<SizeDTO> getAllByProductAbs(long idProductAbs);

    void updateWeightVolume(SizeDTO sizeDTO);
}
