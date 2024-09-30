package catstech.studentmanagement.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "受講生")

@Getter
@Setter

public class Student {

  @NotBlank
  @Pattern(regexp = "^\\d+$", message = "数字のみ入力うるようにして下さい")
  private String id;

  @NotBlank
  private String name;

  @NotBlank
  private String furigana;

  @NotBlank
  private String nickname;

  @NotBlank
  @Email
  private String mailAddress;

  @NotBlank
  private String address;

  private int age;

  @NotBlank
  private String gender;

  private String remark;
  private boolean isDeleted;
}
