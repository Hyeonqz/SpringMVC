package org.example.hellospring.try1.api;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import org.example.hellospring.try1.ExRateData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiTemplate {
	private final ApiExecutor apiExecutor;
	private final ExRateExtractor exRateExtractor;

	// 디폴트 생성자에서 템플릿 주입을 해준다
	public ApiTemplate () {
		this.apiExecutor = new HttpClientApiExecutor();
		this.exRateExtractor = new ErApiExRateExtractor();
	}

	public ApiTemplate (ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
		this.apiExecutor = apiExecutor;
		this.exRateExtractor = exRateExtractor;
	}

	public BigDecimal getForExRate(String url) {
		return this.getForExRate(url, this.apiExecutor, this.exRateExtractor);
	}

	public BigDecimal getForExRate(String url, ApiExecutor apiExecutor) {
		return this.getForExRate(url, apiExecutor, this.exRateExtractor);
	}

	// 로컬변수인지, 멤버 변수인지 잘 체크해야함
	// 특별한 설정 없으면 무조건 싱글톤 빈으로 설정이 된다.

	public BigDecimal getForExRate(String url, ExRateExtractor exRateExtractor) {
		return this.getForExRate(url,this.apiExecutor, exRateExtractor);
	}

	public BigDecimal getForExRate (String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String response;
		try {
			response = new SimpleApiExecutor().execute(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			return extractExRate(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public BigDecimal extractExRate(String response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ExRateData data = mapper.readValue(response, ExRateData.class);
		return data.rates().get("KRW");
	}

}
