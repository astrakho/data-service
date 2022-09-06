package com.example.dataservice.student;

import com.example.dataservice.course.Course;
import com.example.dataservice.studentcourse.StudentCourse;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student")
@Table(name = "student")
public class Student {
    @Id
    @SequenceGenerator(name = "student_sequence",
                       sequenceName = "student_sequence",
                       allocationSize =  1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "student_sequence")
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Double capacity;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "student"
    )
    @JsonIgnore
    private List<StudentCourse> studentCourses = new ArrayList<>();

    public Student() {}

    public Student(Long id, String name, Double capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public Student(String name, Double capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
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
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", capacity=" + capacity + '}';
    }
}
