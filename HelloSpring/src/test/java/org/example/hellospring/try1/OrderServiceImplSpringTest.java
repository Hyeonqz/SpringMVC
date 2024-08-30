package org.example.hellospring.try1;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.example.hellospring.try1.config.OrderConfig;
import org.example.hellospring.try1.order.service.OrderReq;
import org.example.hellospring.try1.order.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
public class OrderServiceImplSpringTest {

	@Autowired
	private OrderService orderService;

	@Test
	@DisplayName("")
	void createOrder() {
	    // given
		var order = orderService.createOrder("0100", BigDecimal.TEN);

		// when

	    // then
		Assertions.assertThat(order.getId()).isGreaterThan(0);
	}

	@Test
	void createOrders() {
	    // given
		List<OrderReq> orderReqs = List.of(
			new OrderReq("0200",BigDecimal.ONE),
			new OrderReq("0201",BigDecimal.TWO)
		);

	    // when
		var orders = orderService.createOrders(orderReqs);

	    // then
		Assertions.assertThat(orders).hasSize(2);
		orders.forEach(o -> Assertions.assertThat(o.getId()).isGreaterThan(0));
	}

	@Test
	void createOrderTransactionTest() {
		// given
		List<OrderReq> orderReqs = List.of(
			new OrderReq("0200",BigDecimal.ONE),
			new OrderReq("0200",BigDecimal.TWO)
		);

		Assertions.assertThatThrownBy(() -> orderService.createOrders(orderReqs))
			.isInstanceOf(DataIntegrityViolationException.class);

		// when
		var orders = orderService.createOrders(orderReqs);

		// then
		Assertions.assertThat(orders).hasSize(2);
		orders.forEach(o -> Assertions.assertThat(o.getId()).isGreaterThan(0));
	}
}
