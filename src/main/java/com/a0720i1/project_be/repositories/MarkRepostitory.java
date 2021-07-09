package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.Mark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepostitory extends JpaRepository<Mark,Integer> {
    List<Mark> findMarkBySubjectResultId(int subReId);
}
