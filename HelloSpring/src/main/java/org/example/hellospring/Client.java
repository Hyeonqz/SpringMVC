package org.example.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

	public static void main (String[] args) throws IOException {
		ObjectFactory factory = new ObjectFactory();
		PaymentService paymentService = factory.paymentService();

		Payment usd = paymentService.prepare(100L, "KRW", BigDecimal.valueOf(50.7));
		System.out.println(usd);


	}
}
