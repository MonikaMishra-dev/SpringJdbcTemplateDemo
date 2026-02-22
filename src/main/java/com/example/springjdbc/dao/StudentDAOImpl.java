package com.example.springjdbc.dao;

import com.example.springjdbc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Student> romMapper = (rs,rowNum)->{
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));

        return student;
    };

    @Override
    public void createStudent(Student student) throws DataAccessException {
        String sql = "INSERT INTO Student (id,name,age) VALUES (?,?,?)";
        jdbcTemplate.update(sql, student.getId(), student.getName(), student.getAge());

    }

    @Override
    public Student getStudentById(Integer id) throws DataAccessException {
        String sql = "SELECT * FROM Student WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,romMapper,id);
    }

    @Override
    public List<Student> listStudents() throws DataAccessException {
        String sql = "SELECT * FROM Student";
        return jdbcTemplate.query(sql,romMapper);
    }

    @Override
    public void updateStudent(Student student) throws DataAccessException {
        String sql = "UPDATE Student SET name = ?, age= ? WHERE id = ?";
        jdbcTemplate.update(sql,student.getName(),student.getAge(),student.getId());

    }

    @Override
    public void deleteStudent(Integer id) throws DataAccessException {

        String sql = "DELETE FROM Student WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
