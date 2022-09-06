package com.example.dataservice.student;

import com.example.dataservice.course.Course;
import com.example.dataservice.studentcourse.StudentCourse;
import com.example.dataservice.studentcourse.StudentCourseId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student daVinci = new Student("Da Vinci", 10D);
            Student chebyshev = new Student("Chebyshev", 10D);
            Student aristotle = new Student("Aristotle", 15D);
            Student leibniz = new Student("Leibniz", 10D);
            Student rembrandt = new Student("Rembrandt", 10D);

            daVinci.addStudentCourse(new StudentCourse(new StudentCourseId(1L, 1L), daVinci,
                                                       new Course("Painting", 5.0D, 10.0D, LocalDate.of(2020, 1, 1),
                                                                  LocalDate.of(2020, 7, 1)),
                                     LocalDateTime.now()));

            daVinci.addStudentCourse(new StudentCourse(new StudentCourseId(2L, 2L), daVinci,
                                                       new Course("Engineering", 4.0D, 50.0D, LocalDate.of(2020, 1, 1),
                                                                  LocalDate.of(2020, 7, 1)), LocalDateTime.now().minusDays(10)));

            studentRepository.save(daVinci);
        };
    }
}
