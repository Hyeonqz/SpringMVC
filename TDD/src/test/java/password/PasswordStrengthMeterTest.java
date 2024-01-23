package password;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {
	private PasswordStrengthMeter meter = new PasswordStrengthMeter();

	private void assertStrength(String password, PasswordStrength expStr) {
		PasswordStrength result = meter.meter(password);
		assertEquals(expStr, result);
	}
	@Test
	void 이름() {
	}

	//모든 암호를 만족하는 경우 로직
	@Test
	void 모든암호_만족하는지() {
		assertStrength("ab122!@AB",PasswordStrength.STRONG);
		assertStrength("abc1!Add",PasswordStrength.STRONG);
	}

	@Test
	void 두가지조건만족_길이는8개미만() {
		assertStrength("ab12!@A",PasswordStrength.NORMAL);
		assertStrength("Ab12@A",PasswordStrength.NORMAL);
	}

	@Test
	void 두가지조건만족_숫자포함안함() {
		assertStrength("abcdESDSD!@",PasswordStrength.NORMAL);
	}

	@Test
	void 테스트데이터가_null값() {
		assertStrength(null,PasswordStrength.INVALID);
	}
	@Test
	void 테스트데이터_빈값() {
		assertStrength("",PasswordStrength.INVALID);
	}

	@Test
	void 대문자포함안함_나머지는만족() {
		assertStrength("abcd123@3",PasswordStrength.NORMAL);
	}

	@Test
	void 길이8글자이상_나머지불충족() {
		assertStrength("12312312",PasswordStrength.WEAK);
	}

	@Test
	void 숫자포함조건만_만족() {
		assertStrength("1212", PasswordStrength.WEAK);
	}

	@Test
	void 대문자포함조건만_만족() {
		assertStrength("ABDSD", PasswordStrength.WEAK);
	}

	@Test
	void 아무조건도만족안함() {
		assertStrength("abc",PasswordStrength.WEAK);
	}
}
