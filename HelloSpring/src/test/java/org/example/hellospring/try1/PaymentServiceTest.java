package org.example.hellospring.try1;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.lang.NonNull;

class PaymentServiceTest {

	@BeforeEach
	public void beforeEach() {
		System.out.println("테스트 실행");
	}

	@DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
	@Test
	void prepare () throws IOException {

	}

	private static void extracted (BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
		PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(500)));
		Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

		assertThat(payment.getExRate()).isEqualTo(exRate);
		assertThat(payment.getConvertedAmount()).isEqualTo(convertedAmount);

		assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
		assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
	}

}