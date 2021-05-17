package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.Photo;

import java.util.List;

public interface PhotoDAO {
    Photo getPhotoLink (int idProduct);
    void add(Photo photo);
    List<Photo> getAllByProductAbs(int idProductAbs);
}
