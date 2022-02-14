package com.security.service.impl;

import com.security.model.Tuition;
import com.security.repository.ITuitionRepo;
import com.security.service.ITuitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TuitionServiceImpl implements ITuitionService {
    @Autowired
    ITuitionRepo tuitionRepo;

    @Override
    public List<Tuition> getTuitionHistory(Long id) {
        return tuitionRepo.getTuitionHistory(id) ;
    }
}
