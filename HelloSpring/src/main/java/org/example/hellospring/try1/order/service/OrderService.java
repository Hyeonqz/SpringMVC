package org.example.hellospring.try1.order.service;

import java.math.BigDecimal;
import java.util.List;

import org.example.hellospring.try1.order.Order;

public interface OrderService {
	Order createOrder (String no, BigDecimal total);
	List<Order> createOrders (List<OrderReq> orders);

}
