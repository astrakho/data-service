package com.example.dataservice.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getCourses();
    }

    @PostMapping
    public void createNewCourse(@Valid @RequestBody Course course) {
        courseService.addNewCourse(course);
    }

    @PutMapping(path = "{courseId}")
    @Transactional
    public void updateCourse(
            @PathVariable("courseId") Long courseId,
            @RequestParam() String courseName,
            @RequestParam() Double credit,
            @RequestParam() LocalDate startDate,
            @RequestParam() LocalDate endDate) {
        courseService.updateCourse(courseId, courseName, credit, startDate, endDate);
    }
}
