package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "course_name",unique = true,nullable = false)
    private String coursesName;

    private int session;

    private int hour;

    @OneToMany(mappedBy = "course")
    private List<Lesson>lessons;
}
