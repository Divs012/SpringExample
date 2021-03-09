package com.divya.JunitExample.controller;

import com.divya.JunitExample.model.Student;
import com.divya.JunitExample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("/Student")
public class StudentController {
    @Autowired
    private StudentService service;
@GetMapping("/allStudents")
@ResponseBody
    public ResponseEntity<Map<Integer,Student>>getAllStudent(){
    Map<Integer, Student> students=service.getAllStudents();
    return new ResponseEntity<Map<Integer,Student>>(students, HttpStatus.OK);

    }
    @GetMapping("/Student/{studentId}")
    @ResponseBody
    public ResponseEntity<Student>getStudentById(@PathVariable int studentId){
    Student student=service.getStudentById(studentId);
        return new ResponseEntity<Student>(student, HttpStatus.OK);

    }
    @PostMapping(value="/addStudent",consumes={"application/json"},produces={"application/json"})
    @ResponseBody
    public ResponseEntity<Student>addStudent(@RequestBody Student student, UriComponentsBuilder builder){
     service.addStudent(student);
        HttpHeaders headers=new HttpHeaders();
        headers.setLocation(builder.path("/addItems{id}").buildAndExpand(student.getStudentId()).toUri());
        return new ResponseEntity<Student>(headers, HttpStatus.CREATED);

    }
    @PutMapping("/updateStudent")
    @ResponseBody
    public ResponseEntity<Student>updateStudent(@RequestBody Student student){
        if(student!=null)
            service.updateStudent(student);
        return new ResponseEntity<Student>(student, HttpStatus.OK);

    }
    @DeleteMapping("deleteStudent/{studentId}")
            @ResponseBody
            public ResponseEntity<Void>deleteStudent(@PathVariable int studentId)
    {
    service.deleteStudent(studentId);
    return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }


}
