package org.example.hellospring.try1.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.stream.Collectors;

public class SimpleApiExecutor implements ApiExecutor{

	@Override
	public String execute (URI uri) throws IOException {
		String response;
		HttpURLConnection connection = (HttpURLConnection)uri.toURL().openConnection();

		// AutoClosable 인터페이스 구현하는 객체는 try 블록에 빠져나가기 전에 close 를 자동으로 해준다.
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			response = br.lines().collect(Collectors.joining());
		}
		return response;
	}

}
