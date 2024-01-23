package password;

public class PasswordStrengthMeter {
	public PasswordStrength meter(String s) {

		if(s==null || s.isEmpty())
			return PasswordStrength.INVALID;

		if(s.length() < 8) {
			return PasswordStrength.NORMAL;
		}

		boolean containsNum = meetsContainingNum(s);
		if(!containsNum) {
			return PasswordStrength.NORMAL;
		}

		return PasswordStrength.STRONG;
	}

	private boolean meetsContainingNum(String s) {
		for(char ch : s.toCharArray()) {
			if(ch>='0' && ch<='9') {
				return true;
			}
		}
			return false;
	}



}
