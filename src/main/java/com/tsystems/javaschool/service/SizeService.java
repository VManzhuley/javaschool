package com.tsystems.javaschool.service;

import com.tsystems.javaschool.dto.SizeDTO;
import com.tsystems.javaschool.entity.product.WeightVolume;

import java.util.List;

public interface SizeService {

    SizeDTO getSize(int idProduct);

    void addWeightVolume(SizeDTO sizeDTO, int idProductAbs);

    SizeDTO mapToSizeDTO(WeightVolume weightVolume);

    List<SizeDTO> allByProductAbs(int idProductAbs);
}
