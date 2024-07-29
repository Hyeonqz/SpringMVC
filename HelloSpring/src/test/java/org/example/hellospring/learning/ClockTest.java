package org.example.hellospring.learning;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClockTest {
	// Clock 을 이용해서 LocalDate.now?
	@Test
	public void clock() {
		Clock clock = Clock.systemDefaultZone();

		LocalDateTime dt1 = LocalDateTime.now(clock);
		LocalDateTime dt2 = LocalDateTime.now(clock);
		Assertions.assertThat(dt1).isEqualTo(dt2);
	}

	// Clock 을 Test 에서 사용할 때 내가 원하는 시간을 지정해서 현재 시간을 가져오게 할 수 있는가?
	@Test
	void fixedClock() {
		Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

		LocalDateTime dt1 = LocalDateTime.now(clock);
		LocalDateTime dt2 = LocalDateTime.now(clock);
		LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(1);

		Assertions.assertThat(dt1).isEqualTo(dt2);
		Assertions.assertThat(dt3).isNotEqualTo(dt1.plusHours(1));

	}
}
