package mm.com.aeon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
@EnableAsync
public class VcsmServiceApplication {
	
	@RequestMapping("/vcsm")
	String sayHello() {
		return "Hello! Welcome to Virtual Card System.";
	}

	public static void main(String[] args) {
		SpringApplication.run(VcsmServiceApplication.class, args);
	}
	
}

