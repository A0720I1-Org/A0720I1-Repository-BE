package com.a0720i1.project_be.services;

import com.a0720i1.project_be.models.SchoolYear;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SchoolYearService {
    List<SchoolYear> getAll();

    List<SchoolYear> findAll();
}
