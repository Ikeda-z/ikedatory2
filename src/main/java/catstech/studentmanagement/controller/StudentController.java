package catstech.studentmanagement.controller;


import catstech.studentmanagement.domain.StudentDetail;
import catstech.studentmanagement.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受け付けるControllerです。
 */

@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生一覧検索です。
   * 全件検索を行うので、条件指定は行わないものになります。
   *
   * @return　受講生一覧(全件)
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList(){
    return service.searchStudentList();
  }

  /**
   * 受講生検索です。
   * IDに紐づく任意の受講生情報を取得します。
   *
   * @param id　受講生ID
   * @return　受講生情報
   */
  @GetMapping("/student/{id}")
  public StudentDetail getStudent (@PathVariable String id) {
    return service.searchStudent(id);
  }

  /**
   * 受講生の新規登録を行います。
   *
   * @param studentDetail　登録する受講生の詳細情報
   * @return　登録された受講生の詳細情報
   */
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(@RequestBody StudentDetail studentDetail){
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生の更新を行います。
   *
   * @param studentDetail　更新する受講生の詳細情報
   * @return　更新処理完了のお知らせを返します。
   */
  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail){
    service.updateStudent(studentDetail);
  return ResponseEntity.ok("更新処理が完了しました");
  }
}
