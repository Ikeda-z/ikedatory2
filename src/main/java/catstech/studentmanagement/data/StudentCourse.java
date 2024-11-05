package catstech.studentmanagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生コース情報")
@Getter
@Setter


public class StudentCourse {


  @Pattern(regexp = "^\\d+$")
  private String id;

  @Valid
  @Pattern(regexp = "^\\d+$")
  private String studentId;

  @Pattern(regexp = "^\\d+$")
  private String courseId;

  @NotBlank
  private String course;


  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;

  private List<StudentCourseStatus> studentCourseStatus;
}
