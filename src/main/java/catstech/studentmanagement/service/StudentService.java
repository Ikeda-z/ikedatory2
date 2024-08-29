package catstech.studentmanagement.service;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourses;
import catstech.studentmanagement.domain.StudentDetail;
import catstech.studentmanagement.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  public List<StudentsCourses> searchStudentsCourseList() {
    return repository.searchStudentsCourses();
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail){
    repository.registerStudent(studentDetail.getStudent());
    //TODO:コース情報登録も行う
    for (StudentsCourses studentsCourse:studentDetail.getStudentsCourses()) {
      studentsCourse.setStudentId(studentDetail.getStudent().getId());
      studentsCourse.setCourseStartDate(LocalDateTime.now());
      studentsCourse.setCourseEndDate(LocalDateTime.now().plusYears(1));
      repository.regiserStudentsCourses(studentsCourse);
    }
  }
}


