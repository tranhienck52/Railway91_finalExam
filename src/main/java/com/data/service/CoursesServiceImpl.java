package com.data.service;

import com.data.entity.Courses;
import com.data.repository.CoursesRepository;
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
    public Page<Courses> getCoursesById(int id, Pageable pageable) {
        return coursesRepo.findById(id,pageable);
    }

    @Override
    public Optional<Courses> findById(int id) {
        return coursesRepo.findById(id);
    }

    @Override
    public Optional<Courses> deleteCourse(int id) {
        coursesRepo.deleteById(id);
        return null;
    }

    @Override
    public Courses saveCourse(Courses courses) {
        return coursesRepo.save(courses);
    }

}

