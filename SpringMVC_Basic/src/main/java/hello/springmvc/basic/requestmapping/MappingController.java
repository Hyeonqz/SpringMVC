package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
public class MappingController {
	private Logger log = LoggerFactory.getLogger(MappingController.class);

	@RequestMapping(value="/hello-basic", method= RequestMethod.GET)
	public String helloBasic() {
		log.info("hellobasic");
		return "ok";
	}

	@GetMapping(value="/mapping-get-v2")
	public String mappingGetV2() {
		log.info("mappping-get-v2");
		return "ok";
	}

	@GetMapping("/mapping/{userId}")
	public String mappingPath(@PathVariable("userId") String userId) {
		log.info("mapppingPath userId={}",userId);
		return "ok";
	}

	@GetMapping("/mapping/users/{userId}/orders/{orderId}")
	public String mappingPath(
		@PathVariable("userId") String userId,
		@PathVariable Long orderId) {
		log.info("mapppingPath userId={}",userId);
		log.info("orderId={}",orderId);
		return "ok";
	}

	@GetMapping(value = "/mapping-param", params = "mode-debug")
		public String mappingParam() {
			log.info("hi");
			return "ok";
		}
	}

}
