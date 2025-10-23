package com.example.student_management_system.repository;

import com.example.student_management_system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("SELECT c.courseName,COUNT(s) FROM Course c LEFT JOIN c.students s GROUP BY c.courseName")
    List<Object[]>studentCount();

    @Query("SELECT c FROM Course c LEFT JOIN c.students s WHERE s IS NULL")
    List<Course> courseWithoutStudent();

    @Query("SELECT c.courseName, COUNT(s) AS studentCount " +
            "FROM Course c LEFT JOIN c.students s " +
            "GROUP BY c.courseName " +
            "ORDER BY COUNT(s) DESC")
    List<Object[]> findTopCoursesByEnrollment(Pageable pageable);
}
