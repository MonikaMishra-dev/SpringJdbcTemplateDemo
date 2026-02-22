package com.example.springjdbc;

import com.example.springjdbc.dao.StudentDAO;
import com.example.springjdbc.model.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringjdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringjdbcApplication.class, args);
	}
    @Bean
    CommandLineRunner run(StudentDAO studentDao) {
        return args -> {
            // Call DAO methods here
            studentDao.createStudent(new Student(1,"Monika",28));
            studentDao.createStudent(new Student(2,"Vaani",2));
            studentDao.createStudent(new Student(3,"Ashutosh",34));
            System.out.println(studentDao.getStudentById(2)); ;
            System.out.println("All Students:");
            studentDao.listStudents().forEach(System.out::println);
            studentDao.updateStudent(new Student(3,"Ashutosh",35));
            studentDao.deleteStudent(1);
        };
    }
}
