package com.imaginnovate.student.controller;

import com.imaginnovate.student.model.Student;
import com.imaginnovate.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

import java.util.Optional;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody Student student) {
        try {
            Student createdStudent = studentService.createStudent(student);
            return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/marks")
    public ResponseEntity<?> updateStudentMarks(@PathVariable Long id,
                                                @RequestParam @Min(0) @Max(100) Integer marks1,
                                                @RequestParam @Min(0) @Max(100) Integer marks2,
                                                @RequestParam @Min(0) @Max(100) Integer marks3) {
        Optional<Student> studentOptional = Optional.ofNullable(studentService.updateStudentMarks(id, marks1, marks2, marks3));
        if (studentOptional.isPresent()) {
            return new ResponseEntity<>(studentOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }
}
