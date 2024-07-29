package org.example.hellospring.try1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration // Bean 들의 관계를 알려준다
@ComponentScan
public class ObjectFactory {

	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exRateProvider());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new WebApiExRateProvider();
	}


}
