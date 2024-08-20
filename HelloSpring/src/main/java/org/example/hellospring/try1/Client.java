package org.example.hellospring.try1;

import java.math.BigDecimal;

import org.example.hellospring.try1.comfig.PaymentConfig;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {

	public static void main (String[] args) {
		BeanFactory factory = new AnnotationConfigApplicationContext(PaymentConfig.class);
		// Bean 을 가져오는 명령
		PaymentService paymentService = factory.getBean(PaymentService.class);

		Payment payment = paymentService.prepare(100L, "USD",BigDecimal.valueOf(50.7));
		Payment payment1 = paymentService.prepare(100L, "JPY",BigDecimal.valueOf(50.7));
		Payment payment2 = paymentService.prepare(100L, "PHP",BigDecimal.valueOf(50.7));
		System.out.println(payment);

		System.out.println("오늘 미국 1달러 가격 = " + payment.getForeignCurrencyAmount() + " 원과 같다.");
		System.out.println("오늘 일본 1엔화 가격 = " + payment1.getForeignCurrencyAmount() + " 원 과 같다.");
		System.out.println("일본 2000엔 가격 : " + 9.13 * 2000.0+"원");
		System.out.println("오늘 필리핀 1페소 가격 = " + payment2.getForeignCurrencyAmount() + " 원 과 같다.");
	}

}