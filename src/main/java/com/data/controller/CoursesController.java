package com.data.controller;

import com.data.Req.CourseCreateReq;
import com.data.Req.CourseUpdateReq;
import com.data.dto.CourseDto;
import com.data.entity.Course;
import com.data.repository.CoursesRepository;
import com.data.service.CoursesService;
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
public class CoursesController {
    @Autowired
    private CoursesRepository coursesRepo;

    @Autowired
    private CoursesService coursesService;

    @Autowired
    private ModelMapper modelMapper;

    //Lấy danh sách khóa học
    @GetMapping("/course")
    public ResponseEntity<?> getAll(Pageable pageable){
        Page<Course> courses = coursesRepo.findAll(pageable);
//        Page<CourseDto>coursesDtos = modelMapper.map(courses,new TypeToken<Page<CourseDto>>(){}.getType());
        Page<CourseDto>courseDtos = courses.map(course -> modelMapper.map(course,CourseDto.class));
            return ResponseEntity.ok(courseDtos);
        }

    //Lấy chi tiết khóa học
    @GetMapping("/course/{id}")
    public  ResponseEntity<?> getCoursesById(@PathVariable int id, Pageable pageable){
        Page<Course> courses = coursesService.getCoursesById(id,pageable);
        Page<CourseDto> courseDtos = courses.map(course -> modelMapper.map(course,CourseDto.class));
        return ResponseEntity.ok(courseDtos);
        }

    @PostMapping("/course/create")
    public ResponseEntity<?> create(@Valid @RequestBody CourseCreateReq courseCreateReq){
        Course courses = modelMapper.map(courseCreateReq, Course.class);
        coursesRepo.save(courses);
        return ResponseEntity.ok("Them khoa hoc moi thanh cong: " + courses.getCoursesName());
    }

    @PutMapping("/course/update/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CourseUpdateReq courseUpdateReq,
                                    @PathVariable("id") int id){
          Optional<Course> opCourse = coursesService.findById(id);
          if (!opCourse.isPresent()){
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy id");
          }else {
              Course course = opCourse.get();
              modelMapper.map(courseUpdateReq,course);
              Course updateCourse = coursesService.saveCourse(course);
              CourseDto courseDto = modelMapper.map(updateCourse,CourseDto.class);
              return ResponseEntity.ok(courseDto);
          }
    }

    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") int id){
        coursesService.deleteCourse(id);
        return ResponseEntity.ok("Delete khoa hoc thanh cong");
    }
}
