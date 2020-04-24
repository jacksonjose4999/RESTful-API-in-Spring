package com.example.demo.data_layer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public  interface Dao<T> {

    Optional<T> get(int id);
    Collection<T> getAll();
    int save(T t);
    int update(T t);
    int delete(int id);
    Page<T> getPage(Pageable pageable);
}
