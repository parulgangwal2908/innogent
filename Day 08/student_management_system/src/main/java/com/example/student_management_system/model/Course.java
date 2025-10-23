package com.example.student_management_system.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String courseInstructor;
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students=new HashSet<>();

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
