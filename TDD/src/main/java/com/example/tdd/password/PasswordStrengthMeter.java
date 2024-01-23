package com.example.tdd.password;

public class PasswordStrengthMeter {
	public PasswordStrength meter(String s) {
		if(s==null || s.isEmpty())
			return PasswordStrength.INVALID;

		int metCounts = getMetCounts(s);

		if(metCounts==1 || metCounts==0) {
			return PasswordStrength.WEAK;
		}

		if(metCounts==2) {
			return PasswordStrength.NORMAL;
		}

		return PasswordStrength.STRONG;
	}

	private int getMetCounts(String s) {
		int metCounts = 0;
		if(s.length()>=8) {
			metCounts++;
		}
		if(meetsContainingNum(s)) {
			metCounts++;
		}
		if(meetsContainingUppercase(s)) {
			metCounts++;
		}
		return metCounts;
	}

	private boolean meetsContainingUppercase(String s) {
		for(char ch : s.toCharArray()) {
			if(Character.isUpperCase(ch)) {
				return true;
			}
		}
		return false;
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
