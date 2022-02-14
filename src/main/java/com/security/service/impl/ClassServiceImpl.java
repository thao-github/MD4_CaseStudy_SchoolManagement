package com.security.service.impl;

import com.security.model.Classes;
import com.security.repository.IClassRepo;
import com.security.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements IClassService {
    @Autowired
    IClassRepo classRepo;
    @Override
    public List<Classes> findAll() {
        return classRepo.findAll();
    }
}
