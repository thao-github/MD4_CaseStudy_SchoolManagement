package com.security.service.impl;

import com.security.model.Classes;
import com.security.repository.IClassesRepo;
import com.security.service.IClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements IClassesService {
    @Autowired
    IClassesRepo classesRepo;


    @Override
    public List<Classes> findAll() {
        return classesRepo.findAll();
    }

    @Override
    public Long countClasses() {
        return classesRepo.countClasses();
    }

    @Override
    public List<Classes> findAllByName(String name) {
        return classesRepo.findAllByName(name);
    }


}
