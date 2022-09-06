package com.example.dataservice.course;

import com.example.dataservice.studentcourse.StudentCourse;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Course")
@Table(name = "course")
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long id;

    private String courseName;

    private Double credit;

    private Double capacity;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "course"
    )
    @JsonIgnore
    private List<StudentCourse> studentCourses = new ArrayList<>();

    public Course() {}

    public Course(String courseName, Double credit, Double capacity, LocalDate startDate, LocalDate endDate) {
        this.courseName = courseName;
        this.credit = credit;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startTime) {
        this.startDate = startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endTime) {
        this.endDate = endTime;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void addStudentCourse(StudentCourse studentCourse) {
        if (!studentCourses.contains(studentCourse)) {
            studentCourses.add(studentCourse);
        }
    }

    public void removeStudentCourse(StudentCourse studentCourse) {
        studentCourses.remove(studentCourse);
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", courseName='" + courseName + '\'' + ", credit=" + credit + ", startDate=" +
               startDate + ", endDate=" + endDate + ", studentCourses=" + studentCourses + '}';
    }
}
