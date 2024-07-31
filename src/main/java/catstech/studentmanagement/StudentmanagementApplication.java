package catstech.studentmanagement;

import ch.qos.logback.core.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class StudentmanagementApplication {

	private String name = "Kitada chise";
	private String age = "20";


	public static void main(String[] args) {
		SpringApplication.run(StudentmanagementApplication.class, args);

	}

	@GetMapping("/studentInfo")
	public String getStudentInfo() {
		return name + " " + age + "歳";
	}

	@PostMapping("/studentInfo")
	public void setstudentInfo(String name, String age) {
		this.name = name;
		this.age =age;

	}
	@PostMapping("/studentname")
	public void updateStudentName(String name){
		this.name = name;

	}

}







