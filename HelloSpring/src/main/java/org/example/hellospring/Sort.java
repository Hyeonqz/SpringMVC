package org.example.hellospring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
	public static void main (String[] args) {
		List<Integer> scores = Arrays.asList(5,7,2,24,849,22,14,14125,27,5,845,94,9,46,435,2135,3);
		Collections.sort(scores);
		scores.forEach(System.out::print);

		System.out.println();

		List<String> strings = Arrays.asList("a","b","dsa","sdad","spring","java");
		Collections.sort(strings, new Comparator<String>() {
			@Override
			public int compare (String o1, String o2) {
				return o1.length() - o2.length();
			}
		});

		strings.forEach(System.out::print);
	}
}
