package com.data.service;

import com.data.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LessonService {
    Page<Lesson>findAll(Pageable pageable);
}
