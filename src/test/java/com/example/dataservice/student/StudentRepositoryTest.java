package com.example.dataservice.student;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentExistsByName() {
        // Given
        String name = "Franz Kafka";
        Student kafka = new Student(1L, name, 10.0);
        underTest.save(kafka);

        // When
        Optional<Student> studentOptional = underTest.findStudentByName(name);

        // Then
        assertThat(studentOptional.isPresent()).isTrue();
    }

    @Test
    void itShouldCheckIfStudentDoesNotExistByName() {
        // Given
        String name = "Franz Kafka";
        Student kafka = new Student(1L, name, 10.0);

        // When
        Optional<Student> studentOptional = underTest.findStudentByName(name);

        // Then
        assertThat(studentOptional.isPresent()).isFalse();
    }
}