package catstech.studentmanagement.domain;

import catstech.studentmanagement.data.Student;
import catstech.studentmanagement.data.StudentsCourse;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
  public class StudentDetail {

    private Student student;
    private List<StudentsCourse> studentsCourses;


}
