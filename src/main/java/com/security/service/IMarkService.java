package com.security.service;

import com.security.model.Mark;

import java.util.List;

public interface IMarkService {
    List<Mark> getStudentMark(Long id);
}
