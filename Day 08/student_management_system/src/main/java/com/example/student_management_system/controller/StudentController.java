package com.example.student_management_system.controller;

import com.example.student_management_system.dto.StudentRequest;
import com.example.student_management_system.dto.StudentResponse;
import com.example.student_management_system.model.Student;
import com.example.student_management_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse>create(@RequestBody  StudentRequest request){
        return ResponseEntity.ok(studentService.createStudent(request));
    }
    @GetMapping
    public ResponseEntity<List<StudentResponse>>getStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse>update(@PathVariable Long id,StudentRequest request){
        return ResponseEntity.ok(studentService.updateStudent(id,request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student Deleted");
    }
    @GetMapping("/students-with-courses")
    public ResponseEntity< List<StudentResponse>>getAllStudentsWithCourses(){
        return ResponseEntity.ok(studentService.getAllStudentsWithCourses());
    }
    @GetMapping("/find-by-course")
    public ResponseEntity <List<StudentResponse>>getByCourseName(@RequestParam String name ){
        List<StudentResponse>students=studentService.getByCourse(name);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/without-courses")
    public ResponseEntity <List<Student>> getStudentsWithoutCourses() {
        return ResponseEntity.ok(studentService.getStudentsWithoutCourses());
    }
    @GetMapping("/search")
    public List<StudentResponse> getStudentsByCityAndInstructor(@RequestParam String city,@RequestParam String instructor) {
        return studentService.getStudentsByCityAndInstructor(city, instructor);
    }
}
