package com.example.springjdbc.dao;

import com.example.springjdbc.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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
//        jdbcTemplate.update(sql, student.getId(), student.getName(), student.getAge());

        jdbcTemplate.update(sql,ps->{
            ps.setInt(1,student.getId());
            ps.setString(2,student.getName());
            ps.setInt(3,student.getAge());}
        );

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
        String sql = "UPDATE Student SET name = :name, age= :age WHERE id = :id";
//        jdbcTemplate.update(sql,student.getName(),student.getAge(),student.getId());
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(student);
        namedParameterJdbcTemplate.update(sql,parameterSource);

    }

    @Override
    public void deleteStudent(Integer id) throws DataAccessException {

        String sql = "DELETE FROM Student WHERE id = ?";
        jdbcTemplate.update(sql,id);
    }
}
