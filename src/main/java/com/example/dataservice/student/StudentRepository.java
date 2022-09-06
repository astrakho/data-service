package com.example.dataservice.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Long> {
    // @Query("SELECT s FROM student s WHERE s.name = ?Laozi")
    Optional<Student> findStudentByName(String Name);
}
