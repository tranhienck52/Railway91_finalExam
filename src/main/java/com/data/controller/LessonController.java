package com.data.controller;

import com.data.Req.LessonCreateReq;
import com.data.dto.LessonDto;
import com.data.entity.Courses;
import com.data.entity.Lesson;
import com.data.repository.CoursesRepository;
import com.data.service.LessonService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
public class LessonController {
    @Autowired
    private CoursesRepository coursesRepo;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/lesson")
    public ResponseEntity<?> findAll(Pageable pageable){
        Page<Lesson> lessons = lessonService.findAll(pageable);
        Page<LessonDto>lessonDtos = modelMapper.map(lessons, new TypeToken<Page<LessonDto>>(){}.getType());
        return ResponseEntity.ok(lessonDtos);
    }

    @PostMapping("/courses/{courseId}/lessons")
    public ResponseEntity<?> create(@Valid @RequestBody LessonCreateReq lessonCreateReq, @PathVariable("courseId") int courseId){
        Optional<Courses> opCourse = coursesRepo.findById(courseId);
        if (!opCourse.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay khoa hoc");
        }else{
            Courses course = opCourse.get();
            Lesson lesson = modelMapper.map(lessonCreateReq,Lesson.class);
            lesson.setCourse(course);
            return ResponseEntity.ok(lesson);
        }
    }
}
