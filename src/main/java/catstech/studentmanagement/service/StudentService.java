package catstech.studentmanagement.service;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourse;
import catstech.studentmanagement.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

  private StudentRepository repository;

  @Autowired
  public StudentService(StudentRepository repository) {
    this.repository = repository;
  }

  public List<Student> searchStudentList() {
    List<Student> allStudents = repository.search();
    List<Student> studentsIn30s = new ArrayList<>();
    for (Student student : allStudents) {
      int age = student.getAge();
      if (age >= 30 && age <= 39) {
        studentsIn30s.add(student);
      }
    }
    return studentsIn30s;
  }

  public List<StudentsCourse> searchStudentsCourseList() {
    List<StudentsCourse> allCourses = repository.searchStudentsCourses();
    List<StudentsCourse> javaCourses = new ArrayList<>();
    for (StudentsCourse courses : allCourses) {
      if ("javaスタンダード".equals(courses.getCourse())) {
        javaCourses.add(courses);
      }
    }
    return javaCourses;
  }
}


