package com.example.student_management_system.controller;

import com.example.student_management_system.dto.CourseResponse;
import com.example.student_management_system.model.Course;
import com.example.student_management_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResponse> create(@RequestBody Course course){
        return ResponseEntity.ok(courseService.createCourse(course));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse>update(@PathVariable Long id,@RequestBody Course course){
        return ResponseEntity.ok(courseService.updateCourse(id,course));
    }
    @GetMapping
    public ResponseEntity<List<CourseResponse>>getAll(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable Long id,@RequestBody Course course){
        courseService.deleteCourse(id);
        return ResponseEntity.ok("course deleted");
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse>getCourseById(@PathVariable Long id,Course course){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    @GetMapping("/student-count")
    public ResponseEntity<List<Map<String,Object>>> getStudentCount(){
        return ResponseEntity.ok(courseService.getStudentCount());
    }
    @GetMapping("/course-without-student")
    public ResponseEntity<List<Course>>courseWithoutStudent(){
        return ResponseEntity.ok(courseService.courseWithoutStudent());
    }
    @GetMapping("/top")
    public List<Map<String, Object>> getTopNCourses(@RequestParam(defaultValue = "3") int limit) {
        return courseService.getTopNCoursesByEnrollment(limit);
    }


}
