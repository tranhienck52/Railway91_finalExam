package com.data.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseCreateForm {
    @NotBlank(message = "{course.coursesName.NotBlank.message}")
     private String coursesName;

    @Min(value = 0, message = "{course.session.Min.message}")
    private int session;

    @Min(value = 0, message = "{course.session.Min.message}")
    private int hour;
}
