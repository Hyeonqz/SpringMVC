package com.example.springbootaws.lombok;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.springbootaws.web.HelloResponseDto;

@WebMvcTest
public class HelloResponseDtoTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void 롬복_기능_테스트() {
		//given
		String name = "test";
		int amount = 1000;
		//when
		HelloResponseDto dto = new HelloResponseDto(name, amount);
		//then
		assertThat(dto.getName()).isEqualTo(name);
		assertThat(dto.getAmount()).isEqualTo(amount);
	}

}
