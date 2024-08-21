package com.imaginnovate.student.service;

import com.imaginnovate.student.model.Student;
import com.imaginnovate.student.dao.StudentRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;


    public Student createStudent(Student student) {
        if (!student.isAgeValid()) {
            throw new ValidationException("Age must be between 15 and 20 years.");
        }
        student.calculateResults();
        return studentRepository.save(student);
    }

    public Student updateStudentMarks(Long id, Integer marks1, Integer marks2, Integer marks3) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ValidationException("Student not found"));

        if (marks1 == null || marks2 == null || marks3 == null) {
            throw new ValidationException("Marks1, Marks2, and Marks3 are mandatory.");
        }
        if (marks1 < 0 || marks1 > 100 || marks2 < 0 || marks2 > 100 || marks3 < 0 || marks3 > 100) {
            throw new ValidationException("Marks must be between 0 and 100.");
        }

        student.setMarks1(marks1);
        student.setMarks2(marks2);
        student.setMarks3(marks3);
        student.calculateResults();

        return studentRepository.save(student);
    }
}
