package com.imaginnovate.student.service;

import com.imaginnovate.student.model.Student;

public interface StudentService {

    public Student createStudent(Student student);

    public Student updateStudentMarks(Long id, Integer marks1, Integer marks2, Integer marks3);
}
