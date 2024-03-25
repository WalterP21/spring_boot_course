package com.example.cruddemo;

import com.example.cruddemo.dao.StudentDAO;
import com.example.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CruddemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
            //createStudent(studentDAO);
            createMultipleStudents(studentDAO);
            //readStudent(studentDAO);
            //queryForStudents(studentDAO);
            //queryForStudentsByLastName(studentDAO);
            //updateStudent(studentDAO);
            //deleteStudent(studentDAO);
            //deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        int deleted = studentDAO.deleteAll();
        System.out.println("Rows deleted: " + deleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        //delete the student
        studentDAO.delete(3);
    }

    private void updateStudent(StudentDAO studentDAO) {
        //find the student
        Student myStudent = studentDAO.findById(1);

        //change the first name to "John"
        myStudent.setFirstName("John");

        //update the student
        studentDAO.update(myStudent);

        //display the updated result
        System.out.println(myStudent);
    }

    private void queryForStudentsByLastName(StudentDAO studentDAO) {
        //get list of students
        List<Student> theStudents = studentDAO.findByLastName("Doe");

        //Display query results
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }

    private void queryForStudents(StudentDAO studentDAO) {
        //get a list of students
        List<Student> theStudents = studentDAO.findAll();

        //display a list of students
        for(Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }

    }

    private void readStudent(StudentDAO studentDAO) {
        //create a student
        System.out.println("Creating new student object ...");
        Student tempStudent = new Student("Daffy","Duck","daffy@example.com");

        //save a student
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        //display the id of the saved student
        int theID = tempStudent.getId();
        System.out.println("The ID of the student id: " + theID);

        //retrieve student based on the id: primary key
        System.out.println("Retrieving student with id: " + theID);
        Student myStudent = studentDAO.findById(theID);

        //display student
        System.out.println("Found the student: " + myStudent);
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        //create multiple students
        System.out.println("Creating new student object...");
        Student tempStudent1 = new Student("John","Doe","john@example.com");
        Student tempStudent2 = new Student("Fred","Doe","fred@example.com");
        Student tempStudent3 = new Student("Jeff","Doe","jeff@example.com");

        //save the student objects
        System.out.println("Saving the students...");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    private void createStudent(StudentDAO studentDAO) {
        //create the student object
        System.out.println("Creating new student object...");
        Student tempStudent = new Student("Paul","Doe","paul@example.com");

        //save the student object
        System.out.println("Saving the student...");
        studentDAO.save(tempStudent);

        //display the id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }
}
