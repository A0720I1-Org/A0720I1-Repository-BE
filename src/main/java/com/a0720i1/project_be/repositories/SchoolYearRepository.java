package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolYearRepository extends JpaRepository<SchoolYear,Integer> {
    SchoolYear getSchoolYearByBeginYearAndEndYear(int beginYear,int endYear) ;
}
