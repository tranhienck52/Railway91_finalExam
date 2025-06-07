package com.data.controller;

import com.data.form.LessonCreateForm;
import com.data.form.LessonUpdateForm;
import com.data.dto.LessonDto;
import com.data.entity.Course;
import com.data.entity.Lesson;
import com.data.repository.CoursesRepository;
import com.data.service.LessonService;
import com.data.validation.LessonIdExists;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
//        Page<LessonDto> lessonDto = modelMapper.map(lessons, new TypeToken<Page<LessonDto>>(){}.getType());
        Page<LessonDto> lessonDtos = lessons.map(lesson -> modelMapper.map(lesson,LessonDto.class));
        return ResponseEntity.ok(lessonDtos);
    }

    @PostMapping("/courses/{courseId}/lessons")
    public ResponseEntity<?> create(@Valid @RequestBody LessonCreateForm lessonCreateForm, @PathVariable("courseId") int courseId){
        Optional<Course> opCourse = coursesRepo.findById(courseId);
            Course course = opCourse.get();
            Lesson lesson = modelMapper.map(lessonCreateForm,Lesson.class);
            lesson.setCourse(course);

            lessonService.saveLesson(lesson);
            LessonDto lessonDto = modelMapper.map(lesson,LessonDto.class);
            return ResponseEntity.ok(lessonDto);
        }


    @GetMapping("/lessons/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") @LessonIdExists int id){
        Optional<Lesson> optionalLesson = lessonService.findById(id);
            Lesson lesson = optionalLesson.get();
            LessonDto lessonDto = modelMapper.map(lesson, LessonDto.class);
            return ResponseEntity.ok(lessonDto);
        }


    @PutMapping("/lessons/{id}")
    public ResponseEntity<?> updateLesson(@Valid @RequestBody LessonUpdateForm lessonUpdateForm, @PathVariable("id") @LessonIdExists int id){
        Optional<Lesson> optionalLesson = lessonService.findById(id);
            Lesson lesson = optionalLesson.get();
            modelMapper.map(lessonUpdateForm,lesson);
            Lesson updateLesson = lessonService.saveLesson(lesson);
            LessonDto lessonDto = modelMapper.map(updateLesson,LessonDto.class);
            return ResponseEntity.ok(lessonDto);
    }

    @DeleteMapping("/lessons/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") @LessonIdExists int id){
        lessonService.deleteById(id);
        return ResponseEntity.ok("Delete thành công lesson");
    }
}
