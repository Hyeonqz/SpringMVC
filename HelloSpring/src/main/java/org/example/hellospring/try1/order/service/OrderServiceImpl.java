package org.example.hellospring.try1.order.service;

import java.math.BigDecimal;
import java.util.List;

import org.example.hellospring.try1.order.Order;
import org.example.hellospring.try1.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service // @Service 스프링이 빈에 등록이 될 때 자동으로 스캔이 되게 해준다.
public class OrderServiceImpl implements OrderService {
	private final OrderRepository orderRepository;

	public OrderServiceImpl (OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Order createOrder (String no, BigDecimal total) {
		Order order = new Order(no, total);

		this.orderRepository.save(order);
		return order;
	}

	@Override
	public List<Order> createOrders (List<OrderReq> orders) {
		return orders.stream().map(req -> createOrder(req.no(), req.total())).toList();
	}

}
