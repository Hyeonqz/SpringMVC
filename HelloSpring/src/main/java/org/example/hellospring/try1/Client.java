package org.example.hellospring.try1;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

	public static void main (String[] args) throws IOException, InterruptedException {
		BeanFactory factory = new AnnotationConfigApplicationContext(ObjectFactory.class);
		// Bean 을 가져오는 명령
		PaymentService paymentService = factory.getBean(PaymentService.class);

		Payment payment = paymentService.prepare(100L, "USD",BigDecimal.valueOf(50.7));
		System.out.println(payment);


	}
}
