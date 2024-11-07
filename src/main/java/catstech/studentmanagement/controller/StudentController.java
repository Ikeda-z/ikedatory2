package catstech.studentmanagement.controller;


import catstech.studentmanagement.domain.StudentDetail;
import catstech.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 受講生の検索や登録、更新などを行うREST APIとして受け付けるControllerです。
 */
@Validated
@RestController
public class StudentController {

  private StudentService service;

  @Autowired
  public StudentController(StudentService service) {
    this.service = service;
  }

  /**
   * 受講生詳細の一覧検索です。 全件検索を行うので、条件指定は行わないものになります。
   *
   * @return　受講生一覧(全件)
   */
  @Operation(summary = "一覧検索", description = "受講生の一覧を検索します")
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生検索です。 IDに紐づく任意の受講生情報を取得します。
   *
   * @param id 　受講生ID
   * @return　受講生情報
   */
  @Operation(summary = "受講生検索", description = "IDに紐づく任意の受講生情報を検索します。IDは数値以外入力するとエラーメッセージを返します。")
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(
      @PathVariable @NotBlank @Pattern(regexp = "^\\d+$") String id) {

    return service.searchStudent(id);
  }

  /**
   * 受講生条件検索です。名前、年齢、メールアドレス（少なくとも一つ）で検索し、該当する受験生情報を返します。
   * 検索条件がすべてNULLの場合は受講生一覧を返します。
   * 該当する受講生が見つからなかったらメッセージを返します。
   *
   * @param name        　名前
   * @param mailAddress 　メールアドレス
   * @param age         　年齢
   * @param address       住所
   * @return　該当した受験生情報
   */
  @GetMapping("/students")
  public ResponseEntity<Object> filteredStudentList(
  @RequestParam(required = false) String name,
  @RequestParam(required = false) String mailAddress,
  @RequestParam(required = false) Integer age,
  @RequestParam(required = false) String address){

    List<StudentDetail> students;
    if (name == null && mailAddress == null && age == null && address == null) {
      students = service.searchStudentList();
    } else
      students = service.searchStudentsList(name, mailAddress, age, address);

    if (students.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("該当する生徒が見つかりませんでした");
    }
    return ResponseEntity.ok(students);
  }

  /**
   * 受講生の新規登録を行います。
   *
   * @param studentDetail 　受講生詳細
   * @return　実行結果
   */
  @Operation(summary = "受講生登録", description = "受講生を登録します")
  @PostMapping("/registerStudent")
  public ResponseEntity<StudentDetail> registerStudent(
      @RequestBody @Valid StudentDetail studentDetail) {
    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います。 キャンセルフラグの更新もここで行います(論理削除)
   *
   * @param studentDetail 　受講生詳細情報
   * @return　実行結果
   */
  @Operation(summary = "受講生情報の更新", description = "既存の受講生情報を更新、キャンセルフラグの更新を行います。更新が完了した場合、成功メッセージを返します。")
  @PutMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody @Valid StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が完了しました");
  }
}


