package com.tsystems.javaschool.dao;

import com.tsystems.javaschool.entity.product.Photo;

import java.util.List;

public interface PhotoDAO {
    Photo getPhotoLink(long idProduct);

    void create(Photo photo);

    List<Photo> getAllByProductAbs(long idProductAbs);

    void update(Photo photo);

    Photo getById(long id);
}
