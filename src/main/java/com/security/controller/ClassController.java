package com.security.controller;


import com.security.model.Classes;
import com.security.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/classes")
public class ClassController {
    @Autowired
    IClassService classService;
    @GetMapping
    public List<Classes> findAll() {
       return classService.findAll();
    }
}
