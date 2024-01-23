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
}
