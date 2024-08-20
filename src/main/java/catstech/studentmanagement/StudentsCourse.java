package catstech.studentmanagement;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourse {

  private String id;
  private String studentId;
  private String course;
  private LocalDateTime courseStartDate;
  private LocalDateTime courseEndDate;
}
