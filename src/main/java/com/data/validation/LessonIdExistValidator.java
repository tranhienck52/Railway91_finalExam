package com.data.validation;

import com.data.repository.LessonRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LessonIdExistValidator implements ConstraintValidator<LessonIdExists, Integer> {
    private final LessonRepository lessonRepo;

    public LessonIdExistValidator(LessonRepository lessonRepo) {
        this.lessonRepo = lessonRepo;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return lessonRepo.existsById(id);
    }
}
