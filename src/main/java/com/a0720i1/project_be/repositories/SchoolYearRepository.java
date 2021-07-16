package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolYearRepository extends JpaRepository<SchoolYear , Integer> {
    SchoolYear getSchoolYearByBeginYearAndEndYear(int beginYear, int endYear);

    @Query(value = "select school_year.id , school_year.begin_year , school_year.end_year from school_year;"
            , nativeQuery = true)
    List<SchoolYear> findAll();

    @Query(value = "select *from school_year ORDER BY id DESC LIMIT 1", nativeQuery = true)
    int getCurrentYearId();
}
