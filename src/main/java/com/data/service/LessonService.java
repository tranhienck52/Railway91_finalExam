package com.data.service;

import com.data.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LessonService {
    Page<Lesson>findAll(Pageable pageable);

    Optional<Lesson> findById(int id);

    void deleteById(int id);

    Lesson saveLesson(Lesson lesson);
}
