package com.example.student_management_system.mapper;

import com.example.student_management_system.dto.CourseResponse;
import com.example.student_management_system.dto.StudentRequest;
import com.example.student_management_system.dto.StudentResponse;
import com.example.student_management_system.model.Course;
import com.example.student_management_system.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentMapper {
    @Autowired
    CourseMapper courseMapper;
    public Student toRequest(StudentRequest req, Set<Course> courses){
        Student s=new Student();
        s.setName(req.getName());
        s.setCity(req.getCity());
        s.setCourses(courses);
        return s ;

    }
    public StudentResponse toResponse(Student student){
        StudentResponse res=new StudentResponse();
        res.setId(student.getId());
        res.setName(student.getName());
        res.setCity(student.getCity());
        List<CourseResponse>courses=student.getCourses().stream().map(courseMapper::toResponse).collect(Collectors.toList());
        res.setCourseName(courses);
        return res;

    }


}
