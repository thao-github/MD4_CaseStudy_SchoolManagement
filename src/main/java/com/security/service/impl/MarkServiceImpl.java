package com.security.service.impl;

import com.security.model.Mark;
import com.security.repository.IMarkRepo;
import com.security.service.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl implements IMarkService {

    @Autowired
    IMarkRepo markRepo;
    @Override
    public List<Mark> getStudentMark(Long id) {
        return markRepo.getStudentMark(id);
    }
}
