package com.project.university.service;

import com.project.university.model.BaseEntity;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends BaseEntity> {

    List<T> findAll();

    T save(T entity);

    Optional<T> findById(Integer id);

    Optional<T> update(Integer id, T entity);

    void deleteById(Integer id);
}
