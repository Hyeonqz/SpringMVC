package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

	//ControllerV3가 넘어오는 것에대한 true/false 구별
	@Override
	public boolean supports(Object handler) {
		return (handler instanceof ControllerV3); //v3만 지원함.
	}

	@Override
	public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
		ControllerV3 controller = (ControllerV3)handler;

		Map<String,String> paramap = createParamMap(request);
		ModelView mv = controller.process(paramap);
		return mv;
	}

	private Map<String,String> createParamMap(HttpServletRequest request) {
		Map<String,String> paramMap = new HashMap<String,String>();
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName,request.getParameter(paramName)));
		return paramMap;
	}

}
