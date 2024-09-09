package catstech.studentmanagement.domain;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourses;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

  public class StudentDetail {

  private Student student;
  private List<StudentsCourses> studentsCourses;

}


