package com.example.springjdbc.dao;

import com.example.springjdbc.model.Student;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface StudentDAO {
    void createStudent(Student student) throws DataAccessException;

    Student getStudentById(Integer id) throws DataAccessException;

    List<Student> listStudents() throws DataAccessException;

    void updateStudent(Student student) throws DataAccessException;

    void deleteStudent(Integer id) throws DataAccessException;
}
