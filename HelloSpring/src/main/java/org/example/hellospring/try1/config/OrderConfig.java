package org.example.hellospring.try1.config;

import javax.sql.DataSource;

import org.example.hellospring.try1.data.JdbcOrderRepository;
import org.example.hellospring.try1.order.DataConfig;
import org.example.hellospring.try1.order.OrderRepository;
import org.example.hellospring.try1.order.service.OrderService;
import org.example.hellospring.try1.order.service.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataConfig.class) //런타임시 DataConfig 클래스 설정을 가지오온다
public class OrderConfig {

	@Bean
	public OrderRepository orderRepository(DataSource dataSource) {
		return new JdbcOrderRepository(dataSource);
	}

	@Bean
	public OrderService orderService(OrderRepository orderRepository) {
		return new OrderServiceImpl(orderRepository);
	}
}
