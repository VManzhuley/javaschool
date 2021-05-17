package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.WeightVolume;

import java.util.List;

public interface WeightVolumeDAO {
    WeightVolume getWVByIdProduct(int idProduct);
    void add(WeightVolume weightVolume);
    List<WeightVolume> getAllByProductAbs(int idProductAbs);

}
