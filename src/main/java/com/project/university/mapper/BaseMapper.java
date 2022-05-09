package com.project.university.mapper;

public abstract class BaseMapper<T> {

    public abstract T updateMap(T source, T target);

    public Object getNotNull(Object source, Object target) {
        return source == null ? target : source;
    }
}
