package catstech.studentmanagement.controller;

import catstech.studentmanagement.controller.converter.StudentConverter;
import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourse;
import catstech.studentmanagement.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public String getStudentList(Model model){
    List<Student> students = service.searchStudentList();
    List<StudentsCourse> studentsCourses = service.searchStudentsCourseList();

    model.addAttribute("studentList", converter.convertStudentDetails(students, studentsCourses));
    return "StudentList";
  }

  @GetMapping("/studentsCourseList")
  public List<StudentsCourse> getStudentsCourseList(){
    return service.searchStudentsCourseList();
  }
}
