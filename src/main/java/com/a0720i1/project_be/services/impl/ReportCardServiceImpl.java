package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.repositories.ReportCardRepository;
import com.a0720i1.project_be.services.ReportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportCardServiceImpl implements ReportCardService {
    @Autowired
    ReportCardRepository reportCardRepository;

    @Override
    public void createReportCard(int classId, int studentId) {
        this.reportCardRepository.createReportCard(classId, studentId);
    }
}
