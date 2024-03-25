package com.example.cruddemo.dao;

import com.example.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //Define feild for entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired//this isn't technically required if the class only has one constructor
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement the saved method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);
        //query to sort by last name: "FROM Student order by lastName asc"

        //return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "From Student where lastName=:theData", Student.class);

        //set query params
        theQuery.setParameter("theData", theLastName);

        //return query results
        return  theQuery.getResultList();
    }

    @Override
    @Transactional//need this since were actually updating the DB
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        //find the student
        Student theStudent = entityManager.find(Student.class, id);

        //delete the student
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("Delete from Student ").executeUpdate();
        return numRowsDeleted;
    }
}
