package com.a0720i1.project_be.services;

import com.a0720i1.project_be.models.SchoolYear;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public interface SchoolYearService {
    List<SchoolYear> getAll();

    List<SchoolYear> findAll();

    SchoolYear getById(int id);

    int getCurrentYearId();
}
