package com.example.student_management_system.service;

import com.example.student_management_system.dto.CourseResponse;
import com.example.student_management_system.mapper.CourseMapper;
import com.example.student_management_system.model.Course;
import com.example.student_management_system.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service

public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    public CourseResponse createCourse(Course course){
        Course saved=courseRepository.save(course);
        return courseMapper.toResponse(saved);
    }
    public CourseResponse updateCourse(Long id,Course course){
        Course existing =courseRepository.findById(id).orElseThrow(()->new RuntimeException("Couse not found" ));
        existing.setCourseName(course.getCourseName());
        existing.setCourseInstructor((course.getCourseInstructor()));
        Course saved=courseRepository.save(existing);
        return courseMapper.toResponse(saved);

    }
    public List<CourseResponse>getAllCourses(){
        return courseRepository.findAll().stream().map(courseMapper::toResponse).collect(Collectors.toList());

    }
    public void deleteCourse(Long id ){
        courseRepository.deleteById(id);
    }
    public CourseResponse getCourseById(Long id){
        Course course=courseRepository.findById(id).orElseThrow(()->new RuntimeException("course not found"));
        return courseMapper.toResponse(course);
    }
    public List<Map<String,Object>>getStudentCount(){
        List<Object[]>results=courseRepository.studentCount();
        List<Map<String,Object>> response =new ArrayList<>();
        for(Object[] row:results){
            Map<String,Object> map=new HashMap<>();
            map.put("courseName",row[0]);
            map.put("studentCount",row[1]);
            response.add(map);
        }
        return response;
    }
    public List<Course> courseWithoutStudent(){
        return courseRepository.courseWithoutStudent();
    }
    public List<Map<String, Object>> getTopNCoursesByEnrollment(int n) {
        return courseRepository.findTopCoursesByEnrollment(PageRequest.of(0, n))
                .stream().map(row -> Map.of("courseName", row[0], "studentCount", row[1]
                ))
                .collect(Collectors.toList());
    }



}
