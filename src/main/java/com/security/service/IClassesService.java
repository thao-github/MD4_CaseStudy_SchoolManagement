package com.security.service;

import com.security.model.Classes;

import java.util.List;


public interface IClassesService {
    List<Classes> findAll();

    Long countClasses();

    List<Classes> findAllByName(String name);
}
