package com.data.Req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseCreateReq {
    @NotBlank(message = "Cần điền tên khóa học")
     private String coursesName;

    @Min(value = 0, message = "Số buổi học phải lớn hơn 0")
    private int session;

    @Min(value = 0, message = "Số giờ học phải lớn hơn 0")
    private int hour;
}
