package com.example.dataservice.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Long addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByName(student.getName());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Student with same name exists");
        }
        studentRepository.save(student);
        return student.getId();
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException(
                    "student with id " + studentId + " does not exist"
            );
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Student s, Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));

        if (s.getName() != null &&
            s.getName().length() > 0 &&
            !Objects.equals(student.getName(), s.getName())) {
            student.setName(s.getName());
        }

        if (s.getCapacity() != null &&
            !Objects.equals(student.getCapacity(), s.getCapacity())) {
            student.setCapacity(s.getCapacity());
        }

        studentRepository.save(student);
    }
}
