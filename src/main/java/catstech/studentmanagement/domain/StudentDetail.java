package catstech.studentmanagement.domain;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentCourse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "受講生詳細")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

  public class StudentDetail {

  @Valid
  private Student student;

  @Valid
  private List<StudentCourse> studentCourseList;

}


