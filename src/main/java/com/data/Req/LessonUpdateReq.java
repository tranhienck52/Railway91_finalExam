package com.data.Req;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LessonUpdateReq {
    @NotBlank(message = "Cần điền tên bài học")
    private String lessonName;

    @Min(value = 0, message = "Số bài học phải lớn hơn 0")
    private int hour;

    @NotBlank(message = "Cần điền mô tả bai học")
    private String describe;
}
