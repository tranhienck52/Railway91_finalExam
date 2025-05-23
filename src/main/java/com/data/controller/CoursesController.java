package com.data.controller;

import com.data.Req.CourseCreateReq;
import com.data.Req.CourseUpdateReq;
import com.data.dto.CourseDto;
import com.data.entity.Courses;
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
        Page<Courses> courses = coursesRepo.findAll(pageable);
        Page<CourseDto>coursesDtos = modelMapper.map(courses,new TypeToken<Page<CourseDto>>(){}.getType());
            return ResponseEntity.ok(coursesDtos);
        }

    //Lấy chi tiết khóa học
    @GetMapping("/course/{id}")
    public  ResponseEntity<?> getCoursesById(@PathVariable int id, Pageable pageable){
        Page<Courses> pageCourses = coursesService.getCoursesById(id,pageable);
        Page<CourseDto> courseDtos = modelMapper.map(pageCourses,new TypeToken<Page<CourseDto>>(){}.getType());
        return ResponseEntity.ok(courseDtos);
        }

    @PostMapping("createCourse")
    public ResponseEntity<?> create(@Valid @RequestBody CourseCreateReq courseCreateReq){
        Courses courses = modelMapper.map(courseCreateReq, Courses.class);
        coursesRepo.save(courses);
        return ResponseEntity.ok("Them khoa hoc moi thanh cong: " + courses.getCoursesName());
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CourseUpdateReq courseUpdateReq,
                                    @PathVariable("id") int id){
          Optional<Courses> opCourse = coursesService.findById(id);
          if (!opCourse.isPresent()){
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy id");
          }else {
              Courses course = opCourse.get();
              modelMapper.map(courseUpdateReq,course);
              Courses updateCourse = coursesService.saveCourse(course);
              return ResponseEntity.ok("Update khoa hoc thanh cong");
          }
    }

    @DeleteMapping("/course/delete/{id}")
    public ResponseEntity<?> detele(@PathVariable("id") int id){
        coursesService.deleteCourse(id);
        return ResponseEntity.ok("Delete khoa hoc thanh cong");
    }
}
