package catstech.studentmanagement.controller;

import catstech.studentmanagement.controller.converter.StudentConverter;
import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourse;
import catstech.studentmanagement.domain.StudentDetail;
import catstech.studentmanagement.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

  private StudentService service;
  private StudentConverter converter;

  @Autowired
  public StudentController(StudentService service, StudentConverter converter) {
    this.service = service;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList(){
    List<Student> students = service.searchStudentList();
    List<StudentsCourse> studentsCourses = service.searchStudentsCourseList();

    return converter.convertStudentDetails(students, studentsCourses);
  }

  @GetMapping("/studentsCourseList")
  public List<StudentsCourse> getStudentsCourseList(){
    return service.searchStudentsCourseList();
  }
}
