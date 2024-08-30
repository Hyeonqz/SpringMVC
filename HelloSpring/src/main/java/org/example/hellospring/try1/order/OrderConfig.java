package org.example.hellospring.try1.order;

import org.example.hellospring.try1.order.service.OrderService;
import org.example.hellospring.try1.order.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {
	@Bean
	public OrderRepository orderRepository() {
		return new JpaOrderRepository();
	}

	@Bean
	public OrderService orderService(JpaTransactionManager jpaTransactionManager) {
		return new OrderServiceImpl(orderRepository(), jpaTransactionManager);
	}
}
