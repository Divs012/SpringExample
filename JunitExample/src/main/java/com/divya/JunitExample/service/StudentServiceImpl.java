package com.divya.JunitExample.service;

import com.divya.JunitExample.repo.StudentRepo;
import com.divya.JunitExample.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo repo;
    @Override
    public Map<Integer, Student> getAllStudents() {
     return (Map<Integer,Student> )repo.getAllStudents();


    }

    @Override
    public void addStudent(Student student) {
        repo.addStudent(student);
    }

    @Override
    public Student getStudentById(int studentId) {

       return  (Student)repo.getStudentById(studentId);
    }

    @Override
    public void updateStudent(Student student) {
          repo.updateStudent(student);
    }

    @Override
    public void deleteStudent(int studentId) {
        repo.deleteStudent(studentId);

    }
}
