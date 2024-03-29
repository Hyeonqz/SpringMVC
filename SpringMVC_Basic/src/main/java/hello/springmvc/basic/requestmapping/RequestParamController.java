package hello.springmvc.basic.requestmapping;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestParamController {
	@RequestMapping("/request-param-v1")
	public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		log.info("username={}, age={}", username, age);
		response.getWriter().write("ok");
	}

	@RequestMapping("/request-param-v2")
	@ResponseBody
	public String requestParamV2(
		@RequestParam("username") String memberName,
			@RequestParam("age") int memberAge) {

		log.info("username={}, age={}", memberName,memberAge);
		return "ok";
	}

	@RequestMapping("/request-param-v3")
	@ResponseBody
	public String requestParamV3(
		@RequestParam String username,
		@RequestParam int age) {

		log.info("username={}, age={}", username,age);
		return "ok";
	}

	@RequestMapping("/request-param-v4")
	@ResponseBody
	public String requestParamV4(String username,int age) {
		log.info("username={}, age={}", username,age);
		return "ok";
	}

	@ResponseBody
	@RequestMapping("/request-param-required")
	public String requestParamRequire(
		@RequestParam(required = false, defaultValue = "guset") String username
		,@RequestParam(required = true, defaultValue = "-1") Integer age) {
		return "ok";
	}

	//Map으로 출력하기.
	@ResponseBody
	@RequestMapping("/request-param-required12")
	public String requestParamRequire1(
		@RequestParam Map<String,Object> paramMap) {

		log.info("username={}, age={}",paramMap.get("username"),paramMap.get("age"));

		return "ok";
	}
}
