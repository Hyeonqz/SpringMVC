package org.example.hellospring.try1;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // Bean 들의 관계를 알려준다
@ComponentScan
public class ObjectFactory {

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exRateProvider(), clock());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new WebApiExRateProvider();
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}


}
