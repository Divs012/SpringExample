package com.divya.JunitExample.service;

import com.divya.JunitExample.model.Student;

import java.util.Map;

public interface StudentService {

    Map<Integer, Student> getAllStudents();



    void addStudent(Student student);

    Student getStudentById(int studentId);

    void updateStudent(Student student);

    void deleteStudent(int studentId);
}
