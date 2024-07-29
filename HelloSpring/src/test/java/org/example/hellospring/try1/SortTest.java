package org.example.hellospring.try1;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SortTest {
	private Sort sort;

	@BeforeEach
	public void beforeEach() {
		sort = new Sort();
		System.out.println(this);
		System.out.println("테스트 시작");
	}

	@Test
	@DisplayName("정렬 테스트")
	void sort() {
	    // given = 준비
	    // when = 실행
		// 불변으로 List 를 생성하면 안됀다. List.of ~ 위 로직은 안됌
		List<String> list = sort.sortByLength(Arrays.asList("AAA", "BB", "C"));

		// then = 검증
		Assertions.assertThat(list).isEqualTo(Arrays.asList("C", "BB", "AAA"));
	}

	@Test
	@DisplayName("정렬 테스트2")
	void sort3Items() {
		// given = 준비
		// when = 실행
		// 불변으로 List 를 생성하면 안됀다. List.of ~ 위 로직은 안됌
		List<String> list = sort.sortByLength(Arrays.asList("AAA", "BB", "C","D"));

		// then = 검증
		Assertions.assertThat(list).isEqualTo(Arrays.asList("C", "D", "BB", "AAA"));
	}

	@Test
	@DisplayName("정렬 테스트3")
	void sortAlreadySorted() {
		// given = 준비
		// when = 실행
		// 불변으로 List 를 생성하면 안됀다. List.of ~ 위 로직은 안됌
		List<String> list = sort.sortByLength(Arrays.asList("AAA", "BBBBB", "CCCCC","DDDDDD"));

		// then = 검증
		Assertions.assertThat(list).isEqualTo(Arrays.asList("AAA", "BBBBB", "CCCCC","DDDDDD"));
	}
}
