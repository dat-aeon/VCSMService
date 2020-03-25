package mm.com.aeon.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/vcsm")
public class SpringBootMainController {

	@GetMapping(value="/hello")
	public String hello() {
		return "Hello World!";
	}
	
}
