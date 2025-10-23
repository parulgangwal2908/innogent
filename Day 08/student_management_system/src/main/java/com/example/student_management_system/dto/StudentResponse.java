package com.example.student_management_system.dto;

import java.util.List;

public class StudentResponse {
    private Long id;
    private String Name;
    private String city;
    private List<CourseResponse> courses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<CourseResponse> getCourseName() {
        return courses;
    }

    public void setCourseName(List<CourseResponse> courses) {
        this.courses = courses;
    }
}
