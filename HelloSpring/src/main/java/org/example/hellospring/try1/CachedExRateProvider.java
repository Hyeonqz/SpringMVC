package org.example.hellospring.try1;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider{
	// 딱 한번 설정이되고, 수정이 되지 않기 위한 final
	private final ExRateProvider exRateProvider;
	private BigDecimal cachedExRate;
	private LocalDateTime cachedExpiredTime;

	public CachedExRateProvider (ExRateProvider exRateProvider) {
		this.exRateProvider = exRateProvider;
	}

	@Override
	public BigDecimal getExRate (String currency) throws IOException {
		if(cachedExRate==null || cachedExpiredTime.isBefore(LocalDateTime.now())) {
			cachedExRate = this.exRateProvider.getExRate(currency);
			cachedExpiredTime = LocalDateTime.now().plusSeconds(3);


			System.out.println("Cache Updated");
		}

		return cachedExRate;
	}

}
