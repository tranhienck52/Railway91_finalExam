package com.data.controller;

import com.data.form.CourseCreateForm;
import com.data.form.CourseFilterForm;
import com.data.form.CourseUpdateForm;
import com.data.dto.CourseDto;
import com.data.entity.Course;
import com.data.repository.CoursesRepository;
import com.data.service.CoursesService;
import com.data.validation.CourseIdExists;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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
public class CoursesController {
    @Autowired
    private CoursesRepository coursesRepo;

    @Autowired
    private CoursesService coursesService;

    @Autowired
    private ModelMapper modelMapper;

    //Lấy danh sách khóa học
    @GetMapping("/course")
    public ResponseEntity<?> getAll(CourseFilterForm form, Pageable pageable){
        Page<Course> courses = coursesService.getall(form,pageable);
        Page<CourseDto>courseDtos = courses.map(course -> modelMapper.map(course,CourseDto.class));
            return ResponseEntity.ok(courseDtos);
        }

    //Lấy chi tiết khóa học
    @GetMapping("/course/{id}")
    public  ResponseEntity<?> getCoursesById(@PathVariable("id") @CourseIdExists int id, Pageable pageable){
        Page<Course> courses = coursesService.getCoursesById(id,pageable);
        Page<CourseDto> courseDtos = courses.map(course -> modelMapper.map(course,CourseDto.class));
        return ResponseEntity.ok(courseDtos);
        }

    @PostMapping("/course/create")
    public ResponseEntity<?> create(@Valid @RequestBody CourseCreateForm courseCreateForm){
        Course courses = modelMapper.map(courseCreateForm, Course.class);
        coursesRepo.save(courses);
        return ResponseEntity.ok("Them khoa hoc moi thanh cong: " + courses.getCoursesName());
    }

    @PutMapping("/course/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CourseUpdateForm courseUpdateForm,
                                    @PathVariable("id") @CourseIdExists int id){
          Optional<Course> opCourse = coursesService.findById(id);
          if (!opCourse.isPresent()){
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy id");
          }else {
              Course course = opCourse.get();
              modelMapper.map(courseUpdateForm,course);
              Course updateCourse = coursesService.saveCourse(course);
              CourseDto courseDto = modelMapper.map(updateCourse,CourseDto.class);
              return ResponseEntity.ok(courseDto);
          }
    }

    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") @CourseIdExists int id){
        coursesService.deleteCourse(id);
        return ResponseEntity.ok("Delete khoa hoc thanh cong");
    }
}
