package org.example.hellospring.try1;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExRateProvider {
	// 인터페이스 안에 메소드는 자동으로 다 public 이다.
	BigDecimal getExRate(String currency) throws IOException;
}
