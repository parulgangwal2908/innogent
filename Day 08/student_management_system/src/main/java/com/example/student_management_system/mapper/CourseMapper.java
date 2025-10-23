package com.example.student_management_system.mapper;

import com.example.student_management_system.dto.CourseResponse;
import com.example.student_management_system.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseResponse toResponse(Course course){
        CourseResponse response=new CourseResponse();
        response.setId(course.getCourseId());
        response.setCourseName(course.getCourseName());
        response.setCourseInstructor(course.getCourseInstructor());
        if(course.getStudents()!=null){
            response.setStudentCount((long)course.getStudents().size());

        }
        else{
            response.setStudentCount(0L);
        }
        return response;
    }
}
