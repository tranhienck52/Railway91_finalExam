package com.data.controller;

import com.data.Req.LessonCreateReq;
import com.data.Req.LessonUpdateReq;
import com.data.dto.LessonDto;
import com.data.entity.Course;
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

import java.util.List;
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
//        Page<LessonDto> lessonDto = modelMapper.map(lessons, new TypeToken<Page<LessonDto>>(){}.getType());
        Page<LessonDto> lessonDtos = lessons.map(lesson -> modelMapper.map(lesson,LessonDto.class));
        return ResponseEntity.ok(lessonDtos);
    }

    @PostMapping("/courses/{courseId}/lessons")
    public ResponseEntity<?> create(@Valid @RequestBody LessonCreateReq lessonCreateReq, @PathVariable("courseId") int courseId){
        Optional<Course> opCourse = coursesRepo.findById(courseId);
        if (!opCourse.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim thay khoa hoc");
        }else{
            Course course = opCourse.get();
            Lesson lesson = modelMapper.map(lessonCreateReq,Lesson.class);
            lesson.setCourse(course);

            lessonService.saveLesson(lesson);
            LessonDto lessonDto = modelMapper.map(lesson,LessonDto.class);
            return ResponseEntity.ok(lessonDto);
        }
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id){
        Optional<Lesson> optionalLesson = lessonService.findById(id);
        if (!optionalLesson.isPresent()){
            return ResponseEntity.badRequest().body("Id khong ton tai");
        }else {
            Lesson lesson = optionalLesson.get();
            LessonDto lessonDto = modelMapper.map(lesson, LessonDto.class);
            return ResponseEntity.ok(lessonDto);
        }
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<?> updateLesson(@RequestBody LessonUpdateReq lessonUpdateReq,@PathVariable("id") int id){
        Optional<Lesson> optionalLesson = lessonService.findById(id);
        if (!optionalLesson.isPresent()){
            return ResponseEntity.badRequest().body("Id không tồn tại");
        }else {
            Lesson lesson = optionalLesson.get();
            modelMapper.map(lessonUpdateReq,lesson);
            Lesson updateLesson = lessonService.saveLesson(lesson);
            LessonDto lessonDto = modelMapper.map(updateLesson,LessonDto.class);
            return ResponseEntity.ok(lessonDto);
        }
    }

    @DeleteMapping("/lessons/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id){
        lessonService.deleteById(id);
        return ResponseEntity.ok("Delete thành công lesson");
    }
}
