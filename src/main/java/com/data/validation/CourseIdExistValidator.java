package com.data.validation;

import com.data.repository.CoursesRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseIdExistValidator implements ConstraintValidator<CourseIdExists, Integer> {
    private final CoursesRepository coursesRepo;

    public CourseIdExistValidator(CoursesRepository coursesRepo) {
        this.coursesRepo = coursesRepo;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return coursesRepo.existsById(id);
    }
}
