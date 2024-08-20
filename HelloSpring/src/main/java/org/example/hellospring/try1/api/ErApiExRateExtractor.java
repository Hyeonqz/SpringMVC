package org.example.hellospring.try1.api;

import java.math.BigDecimal;

import org.example.hellospring.try1.ExRateData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ErApiExRateExtractor implements ExRateExtractor{

	@Override
	public BigDecimal extract (String response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ExRateData data = mapper.readValue(response, ExRateData.class);
		return data.rates().get("KRW");
	}

}
