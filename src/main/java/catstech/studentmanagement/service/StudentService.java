package catstech.studentmanagement.service;

import catstech.studentmanagement.controller.converter.StudentConverter;
import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentCourse;
import catstech.studentmanagement.data.StudentCourseStatus;
import catstech.studentmanagement.domain.StudentDetail;
import catstech.studentmanagement.repository.StudentRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 受講生情報を取り扱うサービスです。
 * 受講生の検索や登録・更新処理を行います。
 */

@Service
public class StudentService {

  private StudentRepository repository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository repository, StudentConverter converter) {
    this.repository = repository;
    this.converter = converter;
  }

  /**
   * 受講生詳細の一覧検索です。
   * 全件検索を行うので、条件指定は行いません。
   *
   * @return　受講生詳細一覧(全件)
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = repository.search();
    List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    List<StudentCourseStatus> studentCourseStatusList = repository.searchStudentCourseStatusList();

    for (StudentCourse course : studentCourseList) {
      List<StudentCourseStatus> statuses = studentCourseStatusList.stream()
          .filter(status -> status.getId().equals(course.getStatusId()))
          .collect(Collectors.toList());
      course.setStudentCourseStatus(statuses);
    }

    return converter.convertStudentDetails(studentList, studentCourseList);
  }
  /**
   * 受講生詳細情報検索（ID）です。
   *
   * @return　受講生詳細
   */
  public StudentDetail searchStudent(String id){
    Student student = repository.searchStudent(id);
    List<StudentCourse> studentCourses = repository.searchStudentsCourses(student.getId());
    List<StudentCourseStatus> studentCourseStatusList = repository.searchStudentCourseStatusList();
    for (StudentCourse course : studentCourses) {
      List<StudentCourseStatus> statuses = studentCourseStatusList.stream()
          .filter(status -> status.getId().equals(course.getStatusId()))
          .collect(Collectors.toList());
      course.setStudentCourseStatus(statuses);
    }
      // 受講生詳細情報に変換して返す
      return new StudentDetail(student, studentCourses);
    }

  /**
   * 受講生詳細情報検索（名前、年齢、メールアドレス）です。
   *
   * @return
   */
  public List<StudentDetail> searchStudentsList(String name, String mailAddress, Integer age ,String address) {
    List<Student> studentList = repository.searchFilteredStudent(name, mailAddress, age ,address);
    List<StudentCourse> studentCourseList = repository.searchStudentCourseList();
    List<StudentCourseStatus> studentCourseStatusList = repository.searchStudentCourseStatusList();
    for (StudentCourse course : studentCourseList) {
      List<StudentCourseStatus> statuses = studentCourseStatusList.stream()
          .filter(status -> status.getId().equals(course.getStatusId()))
          .collect(Collectors.toList());
      course.setStudentCourseStatus(statuses);
    }

    List<StudentDetail> studentDetails = studentList.stream()
        .map(student -> {
          List<StudentCourse> coursesForStudent = studentCourseList.stream()
              .filter(course -> course.getStudentId().equals(student.getId()))
              .collect(Collectors.toList());

          return new StudentDetail(student, coursesForStudent);
        })
        .collect(Collectors.toList());

    return studentDetails;
  }


  /**
   * 受講生の新規登録を行います。
   * 受講生と受講生コース情報を個別に登録し、受講生コース情報には受講生情報を紐づける値や日付情報を設定します。
   *
   * @param studentDetail　受講生詳細
   * @return　登録情報を付与した受講生詳細
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();

    repository.registerStudent(student);
    studentDetail.getStudentCourseList().forEach(studentCourse -> {
      initStudentCourse(studentCourse, student);
      repository.registerStudentCourse(studentCourse);
    });
    return studentDetail;
  }

  /**
   * 受講生コース情報を登録する際の初期情報を設定する。
   *
   * @param studentsCourse　受講生コース情報
   * @param student　受講生
   */
  private void initStudentCourse(StudentCourse studentsCourse, Student student) {
    LocalDateTime now = LocalDateTime.now();

    studentsCourse.setStudentId(student.getId());
    studentsCourse.setCourseStartDate(now);
    studentsCourse.setCourseEndDate(now.plusYears(1));
  }

  /**
   * 受講生詳細の更新を行います。
   * 受講生と受講生コース情報をそれぞれ更新します。
   *
   * @param studentDetail　受講生詳細
   */
  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    repository.updateStudent(studentDetail.getStudent());
    studentDetail.getStudentCourseList()
        .forEach(studentCourse -> repository.updateStudentCourse(studentCourse));
  }
}




