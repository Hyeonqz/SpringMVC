package org.example.hellospring.try1;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.cglib.core.Local;

public class Payment {
	// private 을 사용함으로 써 외부에서 이 필드에 접근을 못하게함. 생성자를 통해서만 접근할 수 있음.
	private Long orderId;
	private String currency;
	private BigDecimal exRate;
	private BigDecimal foreignCurrencyAmount;
	private BigDecimal convertedAmount;
	private LocalDateTime validUntil;

	/*
	* 1) Payment 기본 생성자를 생성 후 Setter 메소드 만들어서 만드는 것은 별로다.
	* */

	/*
	* 2) 생성과 동시에 값을 세팅하는게 편하다, 그러므로 생성자를 사용해서 주입을 하는게 좋다.
	* */

	// 도메인 모델 패턴
	// 생성자를 이용한다.
	// 팩토리 메소드를 만든다. -> 토비형님은 위 방식을 지향한다.
	public Payment (Long orderId, String currency, BigDecimal exRate, BigDecimal foreignCurrencyAmount,
		BigDecimal convertedAmount, LocalDateTime validUntil) {
		this.orderId = orderId;
		this.currency = currency;
		this.exRate = exRate;
		this.foreignCurrencyAmount = foreignCurrencyAmount;
		this.convertedAmount = convertedAmount;
		this.validUntil = validUntil;
	}

	public static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exRate, LocalDateTime now) {
		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime validUntil = now.plusMinutes(30);
		return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
	}

	public boolean isValid(Clock clock) {
		return LocalDateTime.now(clock).isBefore(this.validUntil);
	}

	@Override
	public boolean equals (Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Payment payment = (Payment)o;
		return Objects.equals(orderId, payment.orderId) && Objects.equals(currency, payment.currency)
			&& Objects.equals(exRate, payment.exRate) && Objects.equals(foreignCurrencyAmount,
			payment.foreignCurrencyAmount) && Objects.equals(convertedAmount, payment.convertedAmount)
			&& Objects.equals(validUntil, payment.validUntil);
	}

	@Override
	public int hashCode () {
		return Objects.hash(orderId, currency, exRate, foreignCurrencyAmount, convertedAmount, validUntil);
	}

	@Override
	public String toString () {
		return "Payment{" +
			"orderId=" + orderId +
			", currency='" + currency + '\'' +
			", exRate=" + exRate +
			", foreignCurrencyAmount=" + foreignCurrencyAmount +
			", convertedAmount=" + convertedAmount +
			", validUntil=" + validUntil +
			'}';
	}

	public Long getOrderId () {
		return orderId;
	}

	public String getCurrency () {
		return currency;
	}

	public BigDecimal getExRate () {
		return exRate;
	}

	public BigDecimal getForeignCurrencyAmount () {
		return foreignCurrencyAmount;
	}

	public BigDecimal getConvertedAmount () {
		return convertedAmount;
	}

	public LocalDateTime getValidUntil () {
		return validUntil;
	}

}
