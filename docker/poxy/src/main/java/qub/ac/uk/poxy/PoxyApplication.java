package qub.ac.uk.poxy;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class PoxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoxyApplication.class, args);
	}

	@Autowired
	private ProtConfiguration prot;

//	@RequestMapping("/port")
//	public int index(HttpServletResponse response) throws Exception {
//		return prot.getPort();
//	}
//
//	@Bean
//	public AppApplicationListener appApplicationListener(){
//		return new AppApplicationListener();
//	}

}
