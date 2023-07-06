package io.sn.mpisi2.controllers;

import io.sn.mpisi2.entities.Course;
import io.sn.mpisi2.entities.Student;
import io.sn.mpisi2.services.CoursesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class CourseController {

    private final CoursesService coursesService;

    public CourseController(CoursesService coursesService) {
        this.coursesService = coursesService;
    }

    @RequestMapping("courses")
    public String getCourses(Model model) {
        model.addAttribute("courses", coursesService.getCourses());
        model.addAttribute("newCourse", new Course());
        return "courses/course";
    }

    @RequestMapping("courses/{courseId}/details")
    public String getCourseDetails(@PathVariable int courseId, Model model) throws ClassNotFoundException {
        List<Student> students = this.coursesService.getStudentsOfCourse(courseId);
        model.addAttribute("students", students);
        model.addAttribute("student", new Student());
        model.addAttribute("course", this.coursesService.getCourse(courseId));
        return "courses/courseDetails";
    }

    @PostMapping("courses/{courseId}/details/student")
    public RedirectView addNewStudentInCourse(@PathVariable int courseId, @ModelAttribute Student student) throws ClassNotFoundException {
        coursesService.addStudentsInCourse(courseId, student);
        return new RedirectView("/courses/" + courseId + "/details");
    }

    @PostMapping("courses/edit")
    public RedirectView addCourse(@ModelAttribute Course course) {
        coursesService.addCourse(course);
        return new RedirectView("/courses");
    }


    @RequestMapping("students")
    public String getStudents(Model model) {
        model.addAttribute("students", coursesService.getStudents());
        model.addAttribute("newStudent", new Student());
        return "students/students";
    }


    @PostMapping("students/edit")
    public RedirectView addNewStudent(@PathVariable int courseId, @ModelAttribute Student student) throws ClassNotFoundException {
        coursesService.addStudent(student);
        return new RedirectView("/students");
    }


}
