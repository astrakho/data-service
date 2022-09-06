package com.example.dataservice.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }


    public void addNewCourse(Course course) {
        Optional<Course> courseOptional = courseRepository.findCourseByCourseName(course.getCourseName());
        if (courseOptional.isPresent()) {
            throw new IllegalStateException("Course with same name exists");
        }
        courseRepository.save(course);
    }

    public void updateCourse(Long courseId,
                             String courseName,
                             Double credit,
                             LocalDate startDate,
                             LocalDate endDate) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException(
                        "course with id " + courseId + "does not exist"
                ));
        course.setCourseName(courseName);
        course.setCredit(credit);
        course.setStartDate(startDate);
        course.setEndDate(endDate);
        courseRepository.save(course);
    }
}
