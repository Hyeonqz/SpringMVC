package org.example.hellospring.try1;

import static java.math.BigDecimal.*;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {
	@Autowired
	private BeanFactory beanFactory;

	@BeforeEach
	public void beforeEach () {
		System.out.println("테스트 실행");
	}

	@DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
	@Test
	void prepare () throws IOException {
		PaymentService paymentService = beanFactory.getBean(PaymentService.class);

		Payment payment = paymentService.prepare(1L, "USD", TEN);

		assertThat(payment.getExRate()).isEqualTo(valueOf(1_000));
		assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
	}

}