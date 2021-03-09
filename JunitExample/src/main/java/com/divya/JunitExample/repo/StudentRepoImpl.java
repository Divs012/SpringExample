package com.divya.JunitExample.repo;

import com.divya.JunitExample.model.Student;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class StudentRepoImpl implements StudentRepo {

    public static final String KEY="Student";
    private RedisTemplate<String,Student>template;
    private HashOperations hash;
    public StudentRepoImpl(RedisTemplate<String,Student> template){
        this.template=template;
        hash=template.opsForHash();
    }
    @Override
    public Map<Integer,Student> getAllStudents() {
        return hash.entries(KEY);

    }

    @Override
    public void addStudent(Student student) {
         hash.put(KEY,student.getStudentId(),student);
    }

    @Override
    public Student getStudentById(int studentId) {
       return (Student) hash.get(KEY, studentId);

    }



    @Override
    public void updateStudent(Student student) {
        addStudent(student);

    }

    @Override
    public void deleteStudent(int studentId) {
        hash.delete(KEY, studentId);

    }
}
