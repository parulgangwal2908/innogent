package com.example.student_management_system.repository;

import com.example.student_management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT DISTINCT s FROM Student s LEFT JOIN FETCH s.courses")
    List<Student> findAllWithCourses();

    @Query("SELECT DISTINCT s FROM Student s JOIN FETCH s.courses c WHERE c.courseName= :courseName")
    List<Student>findByCourseName(@Param("courseName")String courseName);

    @Query("SELECT s FROM Student s LEFT JOIN s.courses c WHERE c IS NULL")
    List<Student> findStudentsWithoutCourses();

    @Query("SELECT DISTINCT s FROM Student s JOIN s.courses c WHERE s.city = :city AND c.courseInstructor = :instructor")
    List<Student> findByCityAndCourseInstructor(@Param("city") String city, @Param("instructor") String instructor);
}
