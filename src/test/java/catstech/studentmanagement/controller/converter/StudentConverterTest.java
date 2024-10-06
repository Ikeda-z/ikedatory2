package catstech.studentmanagement.controller.converter;

import static org.assertj.core.api.Assertions.assertThat;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentCourse;
import catstech.studentmanagement.domain.StudentDetail;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentConverterTest {

  private StudentConverter sut;

  @BeforeEach
  void before(){
    sut = new StudentConverter();
  }

  @Test
  void 受講生に受講生コース情報が正しくマッピングされていること(){

    Student student = createStudent();

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId("1");
    studentCourse.setStudentId("1");
    studentCourse.setCourse("javaコース");
    studentCourse.setCourseStartDate(LocalDateTime.now());
    studentCourse.setCourseEndDate(LocalDateTime.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEqualTo(studentCourseList);
  }
  @Test
  void 受講生のリストと受講生コース情報のリストを渡した時に紐づかない受講生コース情報は除外されること(){
    Student student = createStudent();

    StudentCourse studentCourse = new StudentCourse();    studentCourse.setId("1");
    studentCourse.setStudentId("1");
    studentCourse.setCourse("javaコース");
    studentCourse.setCourseStartDate(LocalDateTime.now());
    studentCourse.setCourseEndDate(LocalDateTime.now().plusYears(1));

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual = sut.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourseList()).isEmpty();
  }
  private static Student createStudent() {
    Student student = new Student();
    student.setId("1");
    student.setName("北田ちせ");
    student.setFurigana("きただちせ");
    student.setNickname("にょろにょろ");
    student.setMailAddress("test@example.com");
    student.setAddress("東京都");
    student.setAge(20);
    student.setGender("女性");
    student.setRemark("");
    student.setDeleted(false);
    return student;
  }

}