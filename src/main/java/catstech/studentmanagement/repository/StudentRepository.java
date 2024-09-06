package catstech.studentmanagement.repository;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourses;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

  @Select("SELECT * FROM students WHERE isDeleted = false")
  List<Student> search();

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(String id);

  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCoursesList();

  @Select("SELECT * FROM students WHERE is_Deleted = false")
  List<Student> getAllStudents();

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List <StudentsCourses> searchStudentsCourses(String studnetId);

  @Insert("INSERT INTO students(name,furigana,nickname,mail_address,address,age,gender,remark,isDeleted)"
      + "VALUES(#{name},#{furigana},#{nickname},#{mailAddress},#{address},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert("INSERT INTO students_courses(student_id,  course, course_start_date, course_end_date)"
      + "VALUES(#{studentId}, #{course}, #{courseStartDate}, #{courseEndDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void  regiserStudentsCourses(StudentsCourses studentsCourses);

  @Select("SELECT * FROM students WHERE id = #{id} AND isDeleted = false")
  Student findStudentById(String id);

  @Update("UPDATE students SET name = #{name},furigana = #{furigana},nickname = #{nickname},mail_Address = #{mailAddress},"
      + "address = #{address},age = #{age},gender = #{gender},remark = #{remark},isDeleted =#{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

  @Update("UPDATE students_courses SET course = #{course} WHERE id = #{id}" )
  void  updateStudentsCourses(StudentsCourses studentsCourses);

}


