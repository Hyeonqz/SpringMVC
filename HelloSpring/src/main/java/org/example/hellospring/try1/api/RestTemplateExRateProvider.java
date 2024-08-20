package org.example.hellospring.try1.api;

import java.math.BigDecimal;

import org.example.hellospring.try1.ExRateData;
import org.example.hellospring.try1.ExRateProvider;
import org.springframework.web.client.RestTemplate;

public class RestTemplateExRateProvider implements ExRateProvider {
	private final RestTemplate restTemplate;

	public RestTemplateExRateProvider (RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public BigDecimal getExRate (String currency) {
		String url = "https://open.er-api.com/v6/latest/" + currency;

		return restTemplate.getForObject(url, ExRateData.class).rates().get("KRW");
	}

}
