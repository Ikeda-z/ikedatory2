package catstech.studentmanagement.repository;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourse;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 受講生情報を扱うリポジトリ。
 *
 * 全件検索や単一条件での検索、コース情報の検索が行えるクラスです。
 */

@Mapper
public interface StudentRepository {

  /**
   * 全件検索をします。
   *
   * @return全件検索をした受講生情報の一覧
   */

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentsCourse> searchStudentsCourses();

  @Insert("INSERT INTO students(id,name,furigana,nickname,mail_address,address,age,gender,remark,isDeleted)" + "VALUES(#{id},#{name},#{furigana},#{nickname},#{mailAddress},#{address},#{age},#{gender},#{remark},false)")
  void registerStudent(Student student);

}


