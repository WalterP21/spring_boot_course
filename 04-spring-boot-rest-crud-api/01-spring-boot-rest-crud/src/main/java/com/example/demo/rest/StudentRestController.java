package com.example.demo.rest;

import com.example.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    //make use of @PostConstruct so were making the list only once
    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Wally", "Porzel"));
        theStudents.add(new Student("John", "Doe"));
        theStudents.add(new Student("Bill", "Johnson"));
    }

    //define an endpoint for students
    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    //define endpoint for /students/{studentID
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        //check student id
        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }

        return theStudents.get(studentId);
    }
}
