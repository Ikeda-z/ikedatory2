package catstech.studentmanagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.domain.StudentDetail;
import catstech.studentmanagement.service.StudentService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private StudentService service;

  private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception{
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk())
            .andExpect(content().json("[]"));

    verify(service, times(1)).searchStudentList();
  }



  @Test
  void 受講生検索でで受講生情報が取得できること() throws Exception {
    String studentId = "99";
    Student student = new Student();
    student.setId("テストです。");
    student.setName("北田ちせ");
    student.setFurigana("きただちせ");
    student.setNickname("にょろにょろ");
    student.setMailAddress("test@example.com");
    student.setAddress("東京都");
    student.setGender("女性");

    when(service.searchStudent(studentId)).thenReturn(new StudentDetail());

    mockMvc.perform(get("/student/" + studentId))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(studentId);
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力した時に入力チェックに異常が発生しないこと(){
    Student student = new Student();
    student.setId("テストです。");
    student.setName("北田ちせ");
    student.setFurigana("きただちせ");
    student.setNickname("にょろにょろ");
    student.setMailAddress("test@example.com");
    student.setAddress("東京都");
    student.setGender("女性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(0);
  }

  @Test
  void 受講生詳細の受講生でIDに数字以外を用いたときに入力チェックがかかること(){
    Student student = new Student();
    student.setId("テストです。");
    student.setName("北田ちせ");
    student.setFurigana("きただちせ");
    student.setNickname("にょろにょろ");
    student.setMailAddress("test@example.com");
    student.setAddress("東京都");
    student.setGender("女性");

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations.size()).isEqualTo(1);
    assertThat(violations).extracting("message").containsOnly("数字のみ入力うるようにして下さい");
  }

  @Test
  void 受講生の新規登録時にきちんとデータが送信されること() throws Exception {
    Student student = new Student();
    student.setId("テストです。");
    student.setName("北田ちせ");
    student.setFurigana("きただちせ");
    student.setNickname("にょろにょろ");
    student.setMailAddress("test@example.com");
    student.setAddress("東京都");
    student.setGender("女性");

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(new ArrayList<>());

    when(service.registerStudent(any(StudentDetail.class))).thenReturn(studentDetail);

    mockMvc.perform(post("/registerStudent"))
        .andExpect(status().isOk());

    verify(service,times(1)).registerStudent(any(StudentDetail.class));
  }

  @Test
  void 更新した受講生情報がきちんと送信されること() throws Exception {
    Student student = new Student();
    student.setId("テストです。");
    student.setName("北田ちせ");
    student.setFurigana("きただちせ");
    student.setNickname("にょろにょろ");
    student.setMailAddress("test@example.com");
    student.setAddress("東京都");
    student.setGender("女性");

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourseList(new ArrayList<>());

    doNothing().when(service).updateStudent(any(StudentDetail.class));

    mockMvc.perform(put("updateStudent"))
        .andExpect(status().isOk())
        .andExpect(content().string("更新処理が完了しました"));

    verify(service, times(1)).updateStudent(any(StudentDetail.class));
  }
  @Test
  public void testGetFilteredStudentList() throws Exception {
    mockMvc.perform(get("/students")
            .param("name", "John")
            .param("mailAddress", "john@example.com")
            .param("age", "25"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("John"))
        .andExpect(jsonPath("$[0].mailAddress").value("john@example.com"))
        .andExpect(jsonPath("$[0].age").value(25));
  }


}