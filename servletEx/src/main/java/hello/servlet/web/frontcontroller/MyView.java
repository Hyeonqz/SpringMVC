package hello.servlet.web.frontcontroller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyView {
	private String viewPath;

	public MyView(String viewPath) {
		this.viewPath = viewPath;
	}

	//실제 뷰가 렌더링 되도록 동작하게 만드는 로직
	public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}

	public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws
		ServletException,
		IOException {

		ModelToRequestAttribute(model,request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}

	private void ModelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
		model.forEach( (key,value) -> request.setAttribute(key,value));
	}
}
