package com.example.student_management.repository;

import com.example.student_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Optional with JpaRepository but good practice
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository provides CRUD methods (save, findAll, findById, deleteById)
}
