package io.sn.mpisi2.services;

import io.sn.mpisi2.entities.Course;
import io.sn.mpisi2.entities.Student;

import java.util.List;

public interface CoursesService {
    Course getCourse(int courseId) throws ClassNotFoundException;
    List<Course> getCourses();
    void addCourse(Course course);
    List<Student> getStudentsOfCourse(int courseId) throws ClassNotFoundException;
    Student addStudentsInCourse(int courseId, Student student) throws ClassNotFoundException;

    List<Student> getStudents();
    void addStudent(Student student);
}
