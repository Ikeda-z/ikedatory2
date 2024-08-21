package catstech.studentmanagement.service;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourse;
import catstech.studentmanagement.repository.StudentRepository;
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
    return repository.search();
  }

  public List<StudentsCourse> searchStudentsCourseList() {
    return repository.searchStudentsCourses();
  }
}


