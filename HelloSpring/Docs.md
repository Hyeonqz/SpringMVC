# 토비의 스프링 정리

## 관심사의 분리(SoC)
Separation of Concerns 관심사 끼리 분리하는 것이다 <br>

동작하는 코드를 만들었으면, 코드를 고칠 때마다 테스트를 해봐야 한다 <br>
### 1) 메소드 추출
### 2) 상속을 통한 확장

```java
public abstract class PaymentService {

	public Payment prepare (Long orderId, String currency, BigDecimal foreignCurrencyAmount) throws IOException {
		BigDecimal exRate = getExRate(currency);
		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

		return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
	}

	abstract BigDecimal getExRate (String currency) throws IOException;

}

public class WebApiExRatePaymentService extends PaymentService{

	@Override
	BigDecimal getExRate (String currency) throws IOException {
		URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();

		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String response = br.lines().collect(Collectors.joining());
		br.close();;

		ObjectMapper mapper = new ObjectMapper();
		ExRateData data = mapper.readValue(response, ExRateData.class);
		BigDecimal exRate = data.rates().get("KRW");

		return exRate;
	}

}
```

위 코드처럼 코드를 상속을 통해 분리하여 추상화를 한다 <br>

최상위 클래스는 유지를 하고 재사용을 하면서 기능을 확장 시켜보는 것을 알아보자 <br>

### 클래스의 분리
### 관계 설정 책임의 분리
컴파일시 의존이랑 런타임시 의존은 다르다 <br> 

시간의 흐름에 따라 영향을 주는 것이 다르다 <br>

## 원칙과 패턴
관심사의 분리 원칙을 가지고 코드를 발전시켰다 <br>

### SOLID
1) 개방 폐쇄 원칙(OCP)[Open-Closed-Principle] -> 클래스나 모듈은 확장에는 열려 있어야 하고, 변경에는 닫혀 있어야 한다.
- 확장하는데, 코드는 변경되면 안됀다?
2) 제어의 역전(IoC)[Inversion of Controller] -> 제어권 이전을 통한 제어관계 역전 - 프레임워크의 기본 동작 원리
- 프레임워크 IoC 랑 객체지향 IoC 를 대비해서 보면 많이 헷갈린다..


### 높은 응집도와 낮은 결합도
응집도가 높고, 결합도가 낮아야 좋은 소프트웨어이다, <br>

응집도가 높다는 것은 하나의 모듈이 하나의 책임 또는 관심사에 집중되어있다는 뜻, 변화가 일어날 때 해당 모듈에서 변하는 부분이 크다 <br>
책임과 관심사가 다른 모듈과는 낮은 결합도, 즉 느슨하게 연결된 형태를 유지하는 것이 바람직하다 <br>

즉, 좋은 소프트웨어는 응집도가 높고, 결합도가 낮다 -> 의존이 크지 않다 <br>

### 스프링 컨테이너와 의존관계 주입










