package org.example.hellospring.try1.api;

import java.io.IOException;
import java.net.URI;

public interface ApiExecutor {
	String execute(URI uri) throws IOException;
}
