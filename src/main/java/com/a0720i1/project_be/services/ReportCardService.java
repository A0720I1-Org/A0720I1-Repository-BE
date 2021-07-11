package com.a0720i1.project_be.services;

import org.springframework.stereotype.Service;

@Service
public interface ReportCardService {
    void createReportCard(int classId, int studentId);
}
