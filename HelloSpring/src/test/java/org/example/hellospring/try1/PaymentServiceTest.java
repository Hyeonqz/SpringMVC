package org.example.hellospring.try1;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentServiceTest {
	private Clock clock;

	@BeforeEach
	public void beforeEach() {
		this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
		System.out.println("테스트 실행");
	}

	@DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
	@Test
	void prepare () {
		testAmount(valueOf(500), valueOf(5_000), this.clock);
		testAmount(valueOf(1000), valueOf(10_000), this.clock);
		testAmount(valueOf(1500), valueOf(15_000), this.clock);
	}

	@Test
	@DisplayName("")
	void validUntil() {
	    // given
		PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)),clock);
		Payment payment= paymentService.prepare(1L, "USD", TEN);
	    // when
		LocalDateTime now = LocalDateTime.now(this.clock);
		LocalDateTime expectedValidUntil = now.plusMinutes(30);

	    // then
		Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
	}

	private static void testAmount (BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
		PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

		assertThat(payment.getExRate()).isEqualTo(exRate);
		assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);

		assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
		assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
	}

}