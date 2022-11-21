package com.example.media;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

//./mvnw spring-boot:run
@SpringBootApplication
@RestController
public class MediaApplication {
// 	@Autowired
// 	private MongoTemplate mongoTemplate;
	public static void main(String[] args) {
		SpringApplication.run(MediaApplication.class, args);
	}
	@RequestMapping(value = {"/"}, method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
	public Media getTotal(@RequestParam(value = "module_1", defaultValue = "") String module_1, @RequestParam(value = "mark_1", defaultValue = "") String mark_1,
						  @RequestParam(value = "module_2", defaultValue = "") String module_2, @RequestParam(value = "mark_2", defaultValue = "") String mark_2,
						  @RequestParam(value = "module_3", defaultValue = "") String module_3, @RequestParam(value = "mark_3", defaultValue = "") String mark_3,
						  @RequestParam(value = "module_4", defaultValue = "") String module_4, @RequestParam(value = "mark_4", defaultValue = "") String mark_4,
						  @RequestParam(value = "module_5", defaultValue = "") String module_5, @RequestParam(value = "mark_5", defaultValue = "") String mark_5,
						  HttpServletResponse response,HttpServletRequest request
	) throws IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		String[] marks = {mark_1, mark_2, mark_3, mark_4, mark_5};
		String[] modules = {module_1, module_2, module_3, module_4, module_5};
		boolean error = false;
		ArrayList<String> errorInformation = new ArrayList<>();
		for (int i = 0; i < marks.length; i++) {
			marks[i] = marks[i].trim();
			if (marks[i].contentEquals("")) {
				error = true;
				int j = i + 1;
				String s = "Please input value for mark_" + j + ".";
				errorInformation.add(s);
				continue;
			}
			if (!isNumeric(marks[i])) {
				error = true;
				marks[i] = "";
				int j = i + 1;
				String s = "Please input integer for mark_" + j + " and it should be greater than 0. ";
				errorInformation.add(s);
				continue;
			}
			if (Integer.parseInt(marks[i]) > 100) {
				error = true;
				marks[i] = "";
				int j = i + 1;
				String s = "Mark_" + j + " should be less than 100. ";
				errorInformation.add(s);
			}
		}
		for (int i = 0; i < modules.length; i++) {
			modules[i] = modules[i].trim();
			if (modules[i].contentEquals("")) {
				error = true;
				int j = i + 1;
				String s = "Please input the name of module_" + j + ".";
				errorInformation.add(s);
			}
		}
		Media media = new Media(marks, modules, error, errorInformation);
		double mediaMark = media.media(marks);
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*30);
		session.setAttribute("media",media);
		//mongoTemplate.insert(media, "MedidaFunction");
		return media;
	}
	public static boolean isNumeric(String str) {
		for (int i = str.length(); --i >= 0; ) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}


}
