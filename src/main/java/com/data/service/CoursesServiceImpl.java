package com.data.service;

import com.data.dto.CourseDto;
import com.data.entity.Course;
import com.data.form.CourseFilterForm;
import com.data.repository.CoursesRepository;
import com.data.specification.CourseSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    private CoursesRepository coursesRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<Course> getall(CourseFilterForm form, Pageable pageable) {
        Specification<Course> spec = CourseSpecification.buildSpec(form);
        Page<Course> courses = coursesRepo.findAll(spec, pageable);
        return courses;
    }

    @Override
    public Page<Course> getCoursesById(int id, Pageable pageable) {
        return coursesRepo.findById(id,pageable);
    }

    @Override
    public Optional<Course> findById(int id) {
        return coursesRepo.findById(id);
    }

    @Override
    public void deleteCourse(int id) {
        Course course = coursesRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Không tìm thấy course"));
        if (!course.getLessons().isEmpty()){
            throw new IllegalStateException("Không thể xóa vì đang có lesson");
        }
        coursesRepo.delete(course);
    }

    @Override
    public Course saveCourse(Course courses) {
        return coursesRepo.save(courses);
    }

}

