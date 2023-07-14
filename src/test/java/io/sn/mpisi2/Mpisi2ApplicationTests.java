package io.sn.mpisi2;

import io.sn.mpisi2.dao.StudentDao;
import io.sn.mpisi2.entities.Student;
import io.sn.mpisi2.exceptions.ResourceNotFoundException;
import io.sn.mpisi2.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class Mpisi2ApplicationTests {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetAllUser() {
        int nbrUser = studentDao.getAllStudent().size();
        assertThat(nbrUser).isGreaterThan(0);
    }

    @Test
    void testGetStudentById() {
        Student student = studentDao.getStudentById(1);
        System.out.println(student.getName());
        assertThat(student.getId()).isEqualTo(1);
    }

    @Test
    void testGetUserByIdUsingJPA() {
        Student student = studentRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("Student", "id"));
        assertThat(student.getId()).isEqualTo(1);
    }

}
