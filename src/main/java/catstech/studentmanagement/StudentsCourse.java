package catstech.studentmanagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsCourse {

  private String id;
  private String studentId;
  private String course;
  private String courseStartDate;
  private String courseEndDate;
}
