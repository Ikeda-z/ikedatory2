package catstech.studentmanagement.controller;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourse;
import catstech.studentmanagement.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList(){
    return service.searchStudentList();
  }
  @GetMapping("/studentsCourseList")
  public List<StudentsCourse> getStudentsCourseList(){
    return service.searchStudentsCourseList();
  }
}
