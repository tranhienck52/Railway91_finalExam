package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String lessonName;

    private int hour;

    private String description;

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;
}
