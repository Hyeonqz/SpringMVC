package org.example.hellospring.try1.order;

import java.math.BigDecimal;

import org.example.hellospring.try1.config.OrderConfig;
import org.example.hellospring.try1.order.service.OrderService;
import org.example.hellospring.try1.order.service.OrderServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.JpaTransactionManager;

import jakarta.persistence.EntityManagerFactory;

public class OrderClients {
	public static void main (String[] args) {
		BeanFactory beanFactory = new AnnotationConfigApplicationContext(OrderConfig.class);
		EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);
		JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

		OrderService orderService = beanFactory.getBean(OrderServiceImpl.class);
		Order order1 = orderService.createOrder("0100", BigDecimal.TEN);
		System.out.println(order1);

	}

}
