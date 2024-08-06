package org.example.hellospring.try1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class  WebApiExRateProvider implements ExRateProvider{

	@Override
	public BigDecimal getExRate (String currency)  {
		String url = "https://open.er-api.com/v6/latest/" + currency;

		return runApiForExRate(url);
	}

	private static BigDecimal runApiForExRate (String url) {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}

		String response;
		try {
			response = executeAPI(uri);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		try {
			return extractExRate(response);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private static String executeAPI (URI uri) throws IOException {
		String response;
		HttpURLConnection connection = (HttpURLConnection)uri.toURL().openConnection();

		// AutoClosable 인터페이스 구현하는 객체는 try 블록에 빠져나가기 전에 close 를 자동으로 해준다.
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			response = br.lines().collect(Collectors.joining());
		}
		return response;
	}

	private static BigDecimal extractExRate(String response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ExRateData data = mapper.readValue(response, ExRateData.class);
		return data.rates().get("KRW");
	}

}
