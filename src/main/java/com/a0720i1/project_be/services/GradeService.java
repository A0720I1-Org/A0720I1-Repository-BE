package com.a0720i1.project_be.services;

import com.a0720i1.project_be.models.Grade;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface GradeService {
    List<Grade> getAll();
    Grade getById(int id);
}
