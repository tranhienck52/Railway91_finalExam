package com.data.service;


import com.data.dto.CourseDto;
import com.data.entity.Course;
import com.data.form.CourseFilterForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CoursesService {
    Page<Course> getall(CourseFilterForm form, Pageable pageable);
    Page<Course> getCoursesById(int id, Pageable pageable);
    Optional<Course> findById(int id);

    public void deleteCourse(int id);

    Course saveCourse(Course courses);
}
