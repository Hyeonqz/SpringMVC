package com.example.springbootaws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springbootaws.web.HelloResponseDto;

@Controller
public class HelloController {

	@GetMapping("/hello/dto")
	public HelloResponseDto helloDto(@RequestParam("name") String name,
									 @RequestParam("amount") int amount) {
		return new HelloResponseDto(name, amount);
	}
}
