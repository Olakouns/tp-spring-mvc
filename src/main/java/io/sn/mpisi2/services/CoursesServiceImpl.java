package io.sn.mpisi2.services;

import io.sn.mpisi2.entities.Course;
import io.sn.mpisi2.entities.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CoursesServiceImpl implements CoursesService {

    private static final List<Course> COURSES = new ArrayList<>();
    private static final List<Student> STUDENTS = new ArrayList<>();
    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private static final AtomicInteger ID_COURSE_GENERATOR = new AtomicInteger(0);

    @Override
    public List<Course> getCourses() {
        return COURSES;
    }

    @Override
    public void addCourse(Course course) {
        course.setId(ID_COURSE_GENERATOR.incrementAndGet());
        course.setStudents(new ArrayList<>());
        COURSES.add(course);
    }

    @Override
    public List<Student> getStudentsOfCourse(int courseId) throws ClassNotFoundException {
        return getCourse(courseId).getStudents();
    }

    @Override
    public Student addStudentsInCourse(int courseId, Student student) throws ClassNotFoundException {
        Course course = this.getCourse(courseId);

        if (student.getId() == 0) {
            student.setId(ID_GENERATOR.incrementAndGet());
            STUDENTS.add(student);
        }

        Optional<Student> OldStudent = course.getStudents().stream().filter(student1 -> student1.getId() == student.getId()).findFirst();

        if (OldStudent.isEmpty()) {
            course.getStudents().add(student);
        }

        return student;
    }

    @Override
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @Override
    public void addStudent(Student student) {
        student.setId(ID_GENERATOR.incrementAndGet());
        STUDENTS.add(student);
    }

    @Override
    public Course getCourse(int courseId) throws ClassNotFoundException {
        Optional<Course> course = COURSES.stream().filter(course1 -> course1.getId() == courseId).findFirst();
        if (course.isEmpty()) {
            throw new ClassNotFoundException("Course not  found");
        }
        return course.get();
    }
}
