package org.example.hellospring.try1.data;

import javax.sql.DataSource;

import org.example.hellospring.try1.order.Order;
import org.example.hellospring.try1.order.OrderRepository;
import org.springframework.jdbc.core.simple.JdbcClient;

import jakarta.annotation.PostConstruct;

public class JdbcOrderRepository implements OrderRepository {
	private final JdbcClient jdbcClient;

	public JdbcOrderRepository (DataSource dataSource) {
		this.jdbcClient = JdbcClient.create(dataSource);
	}

	@PostConstruct
	void initDb() {
		jdbcClient.sql(
			"create table orders()"
		).update();
	}

	@Override
	public void save (Order order) {
		Long id = jdbcClient.sql("select * from orders").query(Long.class).single();
	}

}
