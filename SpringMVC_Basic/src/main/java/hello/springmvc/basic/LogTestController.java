package hello.springmvc.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
	private final Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/log-test")
	public String logTest() {
		String name = "스프링";
		String name2 = "스프링부트";

		log.info("info log={}",name); //1단계
		log.debug("debug log={}",name); //2단계
		log.trace("trace log={}, {}",name,name2); //3단게
		log.warn("warn log={}",name); //4단계
		log.error("error log={}",name); //5단계

		return "ok";
	}
}
