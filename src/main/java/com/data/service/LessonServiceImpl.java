package com.data.service;

import com.data.Req.LessonCreateReq;
import com.data.entity.Lesson;
import com.data.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService{
    @Autowired
    private LessonRepository lessonRepo;

    @Override
    public Page<Lesson> findAll(Pageable pageable) {
        return lessonRepo.findAll(pageable);
    }

}
