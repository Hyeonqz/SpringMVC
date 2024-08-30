package org.example.hellospring.try1.config;

import java.time.Clock;

import org.example.hellospring.try1.ExRateProvider;
import org.example.hellospring.try1.PaymentService;
import org.example.hellospring.try1.api.ApiTemplate;
import org.example.hellospring.try1.api.ErApiExRateExtractor;
import org.example.hellospring.try1.api.RestTemplateExRateProvider;
import org.example.hellospring.try1.api.SimpleApiExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class PaymentConfig {
	@Bean
	public PaymentService paymentService() {
		return new PaymentService(exRateProvider(), clock());
	}

	@Bean
	public ApiTemplate apiTemplate() {
		return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(new JdkClientHttpRequestFactory());
	}

	@Bean
	public ExRateProvider exRateProvider() {
		return new RestTemplateExRateProvider(restTemplate());
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}
}
