package com.data.service;

import com.data.entity.Course;
import com.data.repository.CoursesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService {
    private CoursesRepository coursesRepo;

    public CoursesServiceImpl(CoursesRepository coursesRepo) {
        this.coursesRepo = coursesRepo;
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

