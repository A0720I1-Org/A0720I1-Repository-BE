package com.a0720i1.project_be.services;

import com.a0720i1.project_be.models.Grade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GradeService {
    List<Grade> getAll();
}
