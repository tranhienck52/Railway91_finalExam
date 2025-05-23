package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "course_name",unique = true,nullable = false)
    private String coursesName;

    private int session;

    private int hours;

    @OneToMany(mappedBy = "course",fetch = FetchType.EAGER)
    private List<Lesson>lessons;
}
