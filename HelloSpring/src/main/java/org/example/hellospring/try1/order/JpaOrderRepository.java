package org.example.hellospring.try1.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class JpaOrderRepository implements OrderRepository {
	@PersistenceContext
	private EntityManager entityManager; // 트랜잭션마다 새로 만들어줘야 한다 원래

	// spring data jpa -> save() 경우 내부 구현에 .persist() 가 구현되어 있다.
	@Override
	public void save (Order order) {
		entityManager.persist(order);
	}

}
