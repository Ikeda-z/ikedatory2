package catstech.studentmanagement.domain;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentCourse;
import catstech.studentmanagement.data.StudentCourseStatus;
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
  @Schema(description = "受講生の基本情報")
  private Student student;

  @Valid
  @Schema(description = "受講生コース情報")
  private List<StudentCourse> studentCourseList;


}


