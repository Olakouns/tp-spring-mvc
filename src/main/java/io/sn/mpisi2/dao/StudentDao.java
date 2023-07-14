package io.sn.mpisi2.dao;

import io.sn.mpisi2.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Student> getAllStudent() {
        try {
            return jdbcTemplate.query("SELECT * FROM STUDENT", new RowMapper<Student>() {
                public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setGrade(rs.getString("grade"));
                    return student;
                }
            });
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public Student getStudentById(int id) {
        RowMapper<Student> studentRowMapper = (rs, rowNum) -> new Student(rs.getInt("id"), rs.getString("name"), rs.getString("grade"));
        return (Student) jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE id=?", new BeanPropertyRowMapper(Student.class), id);
    }

}
