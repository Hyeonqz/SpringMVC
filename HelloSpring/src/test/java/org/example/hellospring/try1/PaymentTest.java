package org.example.hellospring.try1;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentTest {

	@Test
	@DisplayName("")
	void createPrepared() {
	    // given
		Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
	    Payment payment = Payment.createPrepared(
			1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1_000), LocalDateTime.now(clock)
		);
	    // when

	    // then
		Assertions.assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));
		Assertions.assertThat(payment.getValidUntil()).isEqualTo(LocalDateTime.now(clock).plusMinutes(30));
	}

	@Test
	void isValid() {
		Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
		Payment payment = Payment.createPrepared(
			1L, "USD", BigDecimal.TEN, BigDecimal.valueOf(1_000), LocalDateTime.now(clock)
		);

		Assertions.assertThat(payment.isValid(clock)).isTrue();
		Assertions.assertThat(payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES)))).isTrue();
	}
}