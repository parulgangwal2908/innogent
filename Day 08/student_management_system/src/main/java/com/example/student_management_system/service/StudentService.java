package com.example.student_management_system.service;

import com.example.student_management_system.dto.StudentRequest;
import com.example.student_management_system.dto.StudentResponse;
import com.example.student_management_system.mapper.StudentMapper;
import com.example.student_management_system.model.Course;
import com.example.student_management_system.model.Student;
import com.example.student_management_system.repository.CourseRepository;
import com.example.student_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    CourseRepository courseRepository;


    public StudentResponse createStudent(StudentRequest request){
        Set<Course>courses=new HashSet<>(courseRepository.findAllById(request.getCourseIds()));
        Student student=studentMapper.toRequest(request,courses);
        Student saved=studentRepository.save(student);
        return studentMapper.toResponse(saved);
    }
    public List<StudentResponse> getAllStudents(){
        return studentRepository.findAll().stream().map(studentMapper::toResponse).collect(Collectors.toList());

    }
    public StudentResponse updateStudent(Long id,StudentRequest request){
        Optional<Student>optional=studentRepository.findById(id);
        if(optional.isPresent()){
            Student student= optional.get();
            student.setName(request.getName());
            student.setCity(request.getCity());
            Set<Course>courses=new HashSet<>(courseRepository.findAllById(request.getCourseIds()));
            student.setCourses(courses);
            Student updated=studentRepository.save(student);
            return studentMapper.toResponse(updated);

        }
        return null;

    }
    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
    public StudentResponse getStudentById(Long id){
        Optional<Student> optional=studentRepository.findById(id);
        if(optional.isPresent()){
            return studentMapper.toResponse(optional.get());
        }
        return null;
    }
    public List<StudentResponse> getAllStudentsWithCourses(){
        List<Student>students=studentRepository.findAllWithCourses();
        return students.stream().map(studentMapper::toResponse).collect(Collectors.toList());
    }
    public List<StudentResponse>getByCourse(String courseName){
        List<Student>students =studentRepository.findByCourseName(courseName);
        return students.stream().map(studentMapper::toResponse).collect(Collectors.toList());
    }
    public List<Student> getStudentsWithoutCourses() {
        return studentRepository.findStudentsWithoutCourses();

    }
    public List<StudentResponse> getStudentsByCityAndInstructor(String city, String instructor) {
        List<Student> students = studentRepository.findByCityAndCourseInstructor(city, instructor);
        return students.stream().map(studentMapper::toResponse).collect(Collectors.toList());
    }
}
