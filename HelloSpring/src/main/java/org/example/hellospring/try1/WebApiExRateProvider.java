package org.example.hellospring.try1;

import java.math.BigDecimal;

import org.example.hellospring.try1.api.ApiTemplate;

public class WebApiExRateProvider implements ExRateProvider {
	private final ApiTemplate apiTemplate;

	public WebApiExRateProvider (ApiTemplate apiTemplate) {
		this.apiTemplate = apiTemplate;
	}

	@Override
	public BigDecimal getExRate (String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;

		return apiTemplate.getForExRate(url);
	}

}
