package com.divya.JunitExample.repo;

import com.divya.JunitExample.model.Student;

import java.util.Map;

public interface StudentRepo {
    Map<Integer,Student> getAllStudents();

    void addStudent(Student student);

    Student getStudentById(int studentId);

    void updateStudent( Student student);

    void deleteStudent(int studentId);
}
