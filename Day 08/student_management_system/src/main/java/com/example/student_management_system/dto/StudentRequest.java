package com.example.student_management_system.dto;

import java.util.List;
import java.util.Set;

public class StudentRequest {
    private String  name;
    private String city;
    private List<Long> courseIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List <Long> courseIds) {
        this.courseIds = courseIds;
    }
}
