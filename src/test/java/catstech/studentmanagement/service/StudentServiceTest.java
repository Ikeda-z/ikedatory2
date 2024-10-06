package catstech.studentmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import catstech.studentmanagement.controller.converter.StudentConverter;
import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentCourse;
import catstech.studentmanagement.domain.StudentDetail;
import catstech.studentmanagement.repository.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository repository;

  @Mock
  private StudentConverter converter;

  @Mock
  private  StudentService sut;

  @BeforeEach
  void before(){
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧検索機能_全件検索が動作すること(){
    //事前準備
    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();

    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);
    //実行
    sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    //、↓があるとテストが成功されません。postmanでは全件検索できているので、メソッドは呼び出しできているとおもうのですが、、。少しヒントをいただけないでしょうか。
    verify(converter, times(1)).convertStudentDetails(studentList,studentCourseList);

  }


  @Test
  void 受講生IDによる受講生詳細検索(){
    String studentId = "1";
    Student student = new Student();
    student.setId(studentId);
    List<StudentCourse> studentCourseList = new ArrayList<>();

    when(repository.searchStudent(studentId)).thenReturn(student);
    when(repository.searchStudentsCourses(studentId)).thenReturn(studentCourseList);

   StudentDetail result = sut.searchStudent(studentId);

    verify(repository, times(1)).searchStudent(studentId);
    verify(repository, times(1)).searchStudentsCourses(studentId);
    assertNotNull(result);
    assertEquals(student, result.getStudent());
    assertEquals(studentCourseList, result.getStudentCourseList());


  }

  @Test
  void 受講生情報の新規登録(){
    Student student = new Student();
    StudentDetail studentDetail = new StudentDetail(student, new ArrayList<>());
    StudentCourse studentCourse = new StudentCourse();
    studentDetail.getStudentCourseList().add(studentCourse);

    doNothing().when(repository).registerStudent(student);
    doNothing().when(repository).regiserStudentCourse(studentCourse);

    StudentDetail result = sut.registerStudent(studentDetail);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).regiserStudentCourse(studentCourse);
    assertEquals(studentDetail, result);

  }

  @Test
  void 受講生情報の更新(){
    StudentDetail studentDetail = new StudentDetail();
    Student student = new Student();

    List<StudentCourse> studentCourseList = new ArrayList<>();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(studentCourseList);

    sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(student);
    for (StudentCourse studentCourse : studentCourseList){
      verify(repository, times(1)).updateStudentCourse(studentCourse);
    }
  }


}