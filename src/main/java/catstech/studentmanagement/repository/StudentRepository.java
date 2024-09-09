package catstech.studentmanagement.repository;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourses;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 受講生テーブルと受講生コーステーブルと紐づくRepositoryです。
 */

@Mapper
public interface StudentRepository {

  /**
   * 受講生の全件検索をします。
   *
   * @return　受講生一覧(全件)
   */

  @Select("SELECT * FROM students")
  List<Student> search();

  /**
   * 受講生の検索を行います。
   *
   * @param id　受講生ID
   * @return　受講生
   */
  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(String id);

  /**
   *  受講生のコース情報の全件検索を行います。
   *
   * @return　受講生のコース情報(全件)
   */
  @Select("SELECT * FROM students_courses")
  List<StudentsCourses> searchStudentsCoursesList();

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   *
   * @param studnetId　受講生ID
   * @return　受講生IDに紐づく受講生コース情報
   */
  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List <StudentsCourses> searchStudentsCourses(String studnetId);

  /**
   * 受講生の情報をデータベースに登録します。
   *
   * @param student　登録する受講生の情報
   */
  @Insert("INSERT INTO students(name,furigana,nickname,mail_address,address,age,gender,remark,isDeleted)"
      + "VALUES(#{name},#{furigana},#{nickname},#{mailAddress},#{address},#{age},#{gender},#{remark},false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  /**
   * 受講生コース情報をデータベースに登録します。
   *
   * @param studentsCourses　登録する受講生のコース情報
   */
  @Insert("INSERT INTO students_courses(student_id,  course, course_start_date, course_end_date)"
      + "VALUES(#{studentId}, #{course}, #{courseStartDate}, #{courseEndDate})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void  regiserStudentsCourses(StudentsCourses studentsCourses);

  /**
   * 更新した受講生情報をデータベースに登録します。
   *
   * @param student　更新する受講生情報
   */
  @Update("UPDATE students SET name = #{name},furigana = #{furigana},nickname = #{nickname},mail_Address = #{mailAddress},"
      + "address = #{address},age = #{age},gender = #{gender},remark = #{remark},isDeleted =#{isDeleted} WHERE id = #{id}")
  void updateStudent(Student student);

  /**
   * 更新する受講生コース情報をデータベースに登録します。
   *
   * @param studentsCourses　更新する受講生コース情報
   */
  @Update("UPDATE students_courses SET course = #{course} WHERE id = #{id}" )
  void  updateStudentsCourses(StudentsCourses studentsCourses);

}


