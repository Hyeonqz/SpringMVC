package org.example.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider{

	@Override
	public BigDecimal getExRate (String currency) throws IOException {
		if(currency.equalsIgnoreCase("KRW"))
			return BigDecimal.valueOf(10000);

		throw new IllegalArgumentException("지원되지 않는 통화 입니다");
	}

}
