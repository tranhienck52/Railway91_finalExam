package com.data.service;


import com.data.entity.Courses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CoursesService {
    Page<Courses> getCoursesById(int id, Pageable pageable);
    Optional<Courses> findById(int id);

    Optional<Courses> deleteCourse(int id);

    Courses saveCourse(Courses courses);
}
