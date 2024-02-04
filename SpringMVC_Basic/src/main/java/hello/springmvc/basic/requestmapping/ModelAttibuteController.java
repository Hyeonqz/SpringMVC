package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ModelAttibuteController {

	@RequestMapping("/model-attribute-v1")
	public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
		HelloData helloData = new HelloData();
		helloData.setUsername(username);
		helloData.setAge(age);

		log.info(helloData.getUsername(),helloData.getAge());
		log.info(String.valueOf(helloData));

		return "ok";
	}

	@RequestMapping("/model-attribute-v2")
	public String modelAttributeV2(@ModelAttribute HelloData helloData) {
		log.info(helloData.getUsername(),helloData.getAge());
		log.info(String.valueOf(helloData));

		return "ok";
	}

	// => @ModelAttribute 생략도 가능하다.
	@RequestMapping("/model-attribute-v3")
	public String modelAttributeV3(HelloData helloData) {
		log.info(helloData.getUsername(),helloData.getAge());
		log.info(String.valueOf(helloData));

		return "ok";
	}
}
