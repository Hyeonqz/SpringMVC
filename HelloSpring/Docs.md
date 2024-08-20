# 토비의 스프링 정리

## 관심사의 분리(SoC)
Separation of Concerns 관심사 끼리 분리하는 것이다 <br>

동작하는 코드를 만들었으면, 코드를 고칠 때마다 테스트를 해봐야 한다 <br>
### 1) 메소드 추출
### 2) 상속을 통한 확장

```java
public abstract class PaymentService {

	public Payment prepare (Long orderId, String currency, BigDecimal foreignCurrencyAmount) {
		BigDecimal exRate = getExRate(currency);
		BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
		LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

		return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
	}

	abstract BigDecimal getExRate (String currency);

}

public class WebApiExRatePaymentService extends PaymentService{

	@Override
	BigDecimal getExRate (String currency) {
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
스프링 컨테이너는 DI, IoC 가 중요한 개념이다 <br>
의존관계 주입 -> Dependency Injection <br>

Spring Bean 은 어플리케이션 기능의 제공하는 것이다 <br>

> @Configuration // Bean 들의 관계를 알려준다

스프링은 Bean Factory 라고 생각하면 된다 <br> 

컨테이너는 뭔가를 담아두는 곳이다, 즉 Spring Bean 을 담아두는 곳이 스프링 컨테이너 이다 <br>

### 싱글톤 레지스트리
싱글톤 패턴을 단점: 테스트가 어렵다 <br>

스프링은 보통 서버 어플리케이션을 만들기 때문에 사용자 요청마다 새로운 오브젝트를 만들면 메모리를 많이 먹고 성능이 떨어진다 <br>
하나를 만들어서 여러 사용자에게 공유를 하는게 좋다 <br>

즉, 싱글톤 오브젝트를 만들어서 사용한다 <br>
```java
		PaymentService paymentService = factory.getBean(PaymentService.class);
		PaymentService paymentService2 = factory.getBean(PaymentService.class);

		System.out.println(paymentService2);
		System.out.println(paymentService);
		System.out.println(paymentService2.hashCode());
		System.out.println(paymentService.hashCode());
		System.out.println(paymentService2.equals(paymentService)); // true
		System.out.println(paymentService2 == paymentService); // true
```

PaymentService 타입의 Bean 을 딱 한가지만 가지고 있다는 뜻이다 <br>

## DI 와 디자인 패턴
- Class 패턴 : 상속
- Object 패턴 : 합성

Object 패턴은 의존관계 주입이 필요하다, 그래서 DI 를 사용해야 한다 <br>

환율정보가 필요할 때 매번 Web API 를 호출해야 할까? <br>
-> 환율 정보 캐시(Cache) 의 도입 <br>

#### 데코레이터 디자인 패턴을 사용한다.
오브젝트에 부가적인 기능/책임 을 동적으로 부여한다 <br>

## 의존성 역전 원칙(DIP)
- 상위 수준 모듈은 하위 수준의 모듈에 의존해서는 안된다. 둘 모두 추상화에 의존해야 한다.
- 추상화는 구체적인 사항에 의존해서는 안 된다. 구체적인 사항은 추상화에 의존해야 한다.


# 테스트
테스트를 만들지 않을 거면 스프링을 도대체 뭐하러 쓰는거죠? <br>

### 수동 테스트의 한계
1. 프린트 된 메시지를 수동으로 확인하는 방법은 불편하다.
2. 사용자 웹 UI 까지 개발한 뒤에 확인하는 방법은 테스트가 실패했을 때 확인할 코드가 많다

가능하면 작은 크기의 자동 수행되는 테스트를 해야 한다 <br>

개발자가 만드는 테스트 <br>
- 개발한 코드에 대한 검증 기능을 코드로 작성한다.
- 자동으로 테스트를 수행하고 결과를 확인한다.
- 테스팅 프레임워크를 활용한다.
- 테스트 작성과 실행도 개발 과정의 일부이다.

### JUnit5
- @Test 테스트 메소드 -> 테스트 기능을 수행하는 메소드다
- @BeforeEach 테스트 -> 각 테스트 전에 실행 된다. 
  - 메소드가 실행되기 전에 위 코드가 먼저 실행이 된다.
  - 모든 테스트에 준비과정을 작성한다.
- 테스트 마다 새로운 인스턴스가 만들어 진다.

```java
public class SortTest {
	Sort sort;

	@BeforeEach
	public void beforeEach() {
		sort = new Sort();
	}

	@Test
	@DisplayName("정렬 테스트")
	void sort() {
	    // given = 준비
	    // when = 실행
		// 불변으로 List 를 생성하면 안됀다. List.of ~ 위 로직은 안됌
		List<String> list = sort.sortByLength(Arrays.asList("AAA", "BB", "C"));

		// then = 검증
		Assertions.assertThat(list).isEqualTo(Arrays.asList("C", "BB", "AAA"));
	}

	@Test
	@DisplayName("정렬 테스트2")
	void sort3Items() {
		// given = 준비
		// when = 실행
		// 불변으로 List 를 생성하면 안됀다. List.of ~ 위 로직은 안됌
		List<String> list = sort.sortByLength(Arrays.asList("AAA", "BB", "C","D"));

		// then = 검증
		Assertions.assertThat(list).isEqualTo(Arrays.asList("C", "D", "BB", "AAA"));
	}

	@Test
	@DisplayName("정렬 테스트3")
	void sortAlreadySorted() {
		// given = 준비
		// when = 실행
		// 불변으로 List 를 생성하면 안됀다. List.of ~ 위 로직은 안됌
		List<String> list = sort.sortByLength(Arrays.asList("AAA", "BBBBB", "CCCCC","DDDDDD"));

		// then = 검증
		Assertions.assertThat(list).isEqualTo(Arrays.asList("AAA", "BBBBB", "CCCCC","DDDDDD"));
	}
}

```

테스트는 가능하면 편하고 빠르게 작성할 수 있도록 연습을 많이 해야 한다 <br>
테스트는 빠르게 실행이 되도록 만들어야 한다 <br>

1) 테스트 스텁 -> 끝의 의미한다. 테스트가 돌아가는 동안에만, 테스트용 오브젝트이다.

### 스프링 DI 를 이용하는 테스트
테스트용 협력자/의존 오브젝트를 스프링의 구성 정보를 이용해서 지정하고 컨테이너로부터 테스트 대상을 가져와서 테스트 <br>
1) @ContextConfiguration
2) @Autowired


```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceSpringTest {
	@Autowired
	private BeanFactory beanFactory;

	@BeforeEach
	public void beforeEach () {
		System.out.println("테스트 실행");
	}

	@DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
	@Test
	void prepare () {
		PaymentService paymentService = beanFactory.getBean(PaymentService.class);

		Payment payment = paymentService.prepare(1L, "USD", TEN);

		assertThat(payment.getExRate()).isEqualTo(valueOf(1_000));
		assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
	}

}
```

### 학습 테스트
직접 만들지 않은 코드, 라이브러리, 레거시 시스템에 대한 테스트 <br>
테스트 대상의 사용방법을 익히고 동작방식을 확인하는데 유용하다 <br>


## 도메인 모델 아키텍쳐 패턴
도메인 로직, 비즈니스 로직을 어디에 둘 지를 결정하는 패턴

1) 트랜잭션 스크립트 - 서비스 메소드(PaymentService.prepare)
2) 도메인 모델 - 도메인 모델 오브젝트(Payment)
   - 위 모델은 테스트를 만들기가 간편하다.


### 템플릿 Template
개방폐쇄원칙: 클래스나 모듈은 확장에는 열려있어야하고 변경에는 닫혀 있어야 한다 <br>

코드 중에서 변경이 거의 일어나지 않으며 일정한 패턴으로 유지되는 특성을 가진 부분을 자유롭게 변경되는 성질을 가진 부분으로서 독립시켜서 효과적으로 활용할 수 있도록 하는 방법 <br>
자유롭게 변경된다 -> 콜백 <br>

### 콜백
콜백은 실행되는 것을 목적으로 다른 오브젝트의 메소드에 전달되는 오브젝트 파라미터로 전달되지만 값을 참조하기 위한 것이 아니라 특정 로직을 담은 메소드를 실행시키는 것이 목적 <br>

하나의 메소드를 가진 인터페이스 타입(SAM) 의 오브젝트 또는 람다 오브젝트 <br>

### 템플릿/콜백 전략 패턴의 특별한 케이스
템플릿은 전략 패턴의 컨텍스트 <br>
콜백은 전략패턴의 전략 이다.

-> 파라미터로 객체를 받아온다? 템플릿 메소드 패턴이다 <br>

### 메소드 주입
의존 오브젝트가 메소드 호출 시점에 파라미터로 전달되는 방식 <br>
의존관계 주입의 한 종류 <br>
메소드 호출 주입이라고도 한다 -> DI 랑 비슷한 느낌이다 <br>

### 스프링이 제공하는 템플릿
1) RestTemplate
2) JdbcTemplate
3) JmsTemplate 
4) HibernateTemplate
5) TransactionTemplate

등등 여러가지 템플릿으로 끝나는 클래스들이 스프링이 구현되 있습니다 <br>

- RestTemplate
  - HTTP API 요청을 처리하는 템플릿
    - HTTP Client 라이브러리 확장 : ClientHttpRequestFactory
    - MessageBody 를 변환하는 전략 : HttpMessageConverter