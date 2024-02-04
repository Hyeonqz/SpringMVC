package hello.springmvc.basic;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RequestBodyStringController {

	@PostMapping("/request-body-string-v1")
	public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

		log.info("message body: " + messageBody);
		response.getWriter().write("ok");
	}
	@PostMapping("/request-body-string-v2")
	public HttpEntity<String> requestBodyStringV1(HttpEntity<String> httpEntity) throws IOException {
		//메시지 컨버터 작동
		String body = httpEntity.getBody();
		log.info("messageBody={}", body);

		return new HttpEntity<>("ok");
	}

	@PostMapping("/request-body-string-v2")
	@ResponseBody
	public HttpEntity<String> requestBodyStringV2(@RequestBody String messageBody) throws IOException {
		//메시지 컨버터 작동
		log.info("messageBody={}", messageBody);

		return new HttpEntity<>("ok");
	}
}
