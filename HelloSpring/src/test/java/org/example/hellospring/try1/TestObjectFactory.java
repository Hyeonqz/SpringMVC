package org.example.hellospring.try1;

import java.math.BigDecimal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // Bean 들의 관계를 알려준다
@ComponentScan
public class TestObjectFactory {

		@Bean
		public PaymentService paymentService () {
			return new PaymentService(exRateProvider());
		}

		@Bean
		public ExRateProvider exRateProvider () {
			return new ExRateProviderStub(BigDecimal.valueOf(1_1100));
		}

	}