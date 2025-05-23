package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@Table
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String lessonName;

    private int hour;

    private String describe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    private Courses course;
}
