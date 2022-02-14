package com.security.service;

import com.security.model.Tuition;

import java.util.List;

public interface ITuitionService {
    List<Tuition> getTuitionHistory(Long id);
}
