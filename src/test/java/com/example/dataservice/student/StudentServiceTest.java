package com.example.dataservice.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new StudentService(studentRepository);
    }

    @Test
    void canGetStudents() {
        // When
        underTest.getStudents();

        // Then
        verify(studentRepository).findAll();
    }

    @Test
    void canAddStudent() {
        // Given
        String name = "Franz Kafka";
        Student kafka = new Student(1L, name, 10.0);

        // When
        underTest.addNewStudent(kafka);

        // Then
        ArgumentCaptor<Student> studentArgumentCaptor =
                ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentArgumentCaptor.capture());

        Student capturedStudent = studentArgumentCaptor.getValue();

        assertThat(capturedStudent).isEqualTo(kafka);
    }

    @Test
    void willThrowWhenNameIsDuplicated() {
        // Given
        String name = "Franz Kafka";
        Student kafka = new Student(1L, name, 10.0);

        given(studentRepository.findStudentByName(kafka.getName()))
                .willReturn(Optional.of(kafka));

        // When
        // Then
        assertThatThrownBy(() -> underTest.addNewStudent(kafka))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Student with same name exists");
    }

    @Test
    @Disabled
    void deleteStudent() {
    }

    @Test
    @Disabled
    void updateStudent() {
    }
}