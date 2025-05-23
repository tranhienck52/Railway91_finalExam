package com.data.repository;

import com.data.entity.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Integer> {
    Page<Courses> findById(int id, Pageable pageable);
//
//    @Query("FROM Courses WHERE coursesName=:coursesName")
//    Page<Courses> findByCoursesName(String coursesName, Pageable pageable);

//    Page<Courses> findByCoursesName(String coursesName, Pageable pageable);
}
