package catstech.studentmanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentCourse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;


@MybatisTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository sut;


  @Test
  void 受講生の全件検索が行えること() {
    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(5);
  }

  @Test
  void 受講生の登録が行えること() {
    Student student = new Student();
    student.setName("北田ちせ");
    student.setFurigana("きただちせ");
    student.setNickname("にょろにょろ");
    student.setMailAddress("test@example.com");
    student.setAddress("東京都");
    student.setAge(20);
    student.setGender("女性");
    student.setRemark("");
    student.setDeleted(false);

    sut.registerStudent(student);

    List<Student> actual = sut.search();
    assertThat(actual.size()).isEqualTo(6);
  }
  @Test
  void 受講生コースの登録が行えるこ(){
    Student student = new Student();
    StudentCourse studentCourse = new StudentCourse();
    student.setId("1");
    studentCourse.setCourse("Javaコース");

    studentCourse.setStudentId(student.getId());

    sut.registerStudentCourse(studentCourse);

    List<StudentCourse> actual = sut.searchStudentsCourses("1");
    assertThat(actual.size()).isEqualTo(2);
  }

  @Test
  void 受講生情報の更新が行われること() {
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

    sut.updateStudent(student);

    student.setName("new name");
    student.setFurigana("new furigana");
    student.setNickname("new nickname");
    student.setMailAddress("new mailAddress");
    student.setAddress("new address");
    student.setAge(25);
    student.setGender("new gender");
    student.setRemark("new remark");

    sut.updateStudent(student);

    Student updateStudent = sut.searchStudent("1");

    assertThat(updateStudent.getName()).isEqualTo("new name");
    assertThat(updateStudent.getFurigana()).isEqualTo("new furigana");
    assertThat(updateStudent.getNickname()).isEqualTo("new nickname");
    assertThat(updateStudent.getMailAddress()).isEqualTo("new mailAddress");
    assertThat(updateStudent.getAddress()).isEqualTo("new address");
    assertThat(updateStudent.getAge()).isEqualTo(25);
    assertThat(updateStudent.getGender()).isEqualTo("new gender");
    assertThat(updateStudent.getRemark()).isEqualTo("new remark");

  }
  @Test
  void 受講生コース情報の更新が行われること(){
    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId("1");
    studentCourse.setCourse("ITパスポート");

    sut.updateStudentCourse(studentCourse);

    studentCourse.setCourse("javaスタンダード");

    sut.updateStudentCourse(studentCourse);

    List<StudentCourse> updateStudentCourse = sut.searchStudentsCourses("1");

    assertFalse(updateStudentCourse.isEmpty());
    //assertEquals("ITパスポート", updateStudentCourse.get(0).getCourse());
    assertEquals("javaスタンダード",updateStudentCourse.get(0).getCourse());
  }
}




