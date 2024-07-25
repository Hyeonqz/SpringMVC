package org.example.hellospring;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateData(String result, Map<String, BigDecimal> rates) {

/* DTO 역할이랑 같다 -> 파라미터에 해당하는 생성자, getter 알아서 만들어줌*/

}
