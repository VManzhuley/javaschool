package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.WeightVolume;

import java.util.List;

public interface WeightVolumeDAO {
    WeightVolume getWVByIdProduct(long idProduct);

    void create(WeightVolume weightVolume);

    List<WeightVolume> getAllByProductAbs(long idProductAbs);

    void update(WeightVolume weightVolume);

    WeightVolume getById(long id);

}
