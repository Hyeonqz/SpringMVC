package hello.servlet.web.frontcontroller.v3;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*") //*을 해둬야지 맵핑 경로가 모든걸 받을 수 있다.
public class FrontControllerServletV3 extends HttpServlet {

	private Map<String, ControllerV3> controllerV3Map = new HashMap<>();

	public FrontControllerServletV3() {
		controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
		controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
		controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		ControllerV3 controller = controllerV3Map.get(requestURI);
		//예외처리
		if(controller==null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		//paramMap
		Map<String,String> paramMap = createParamMap(request);

		ModelView view = controller.process(paramMap);
		String viewName = view.getViewName(); // 논리이름 new-form
		

/*		MyView myview = new MyView("/WEB-INF/views/" + viewName + ".jsp");
		myview.render(request,response);*/
		MyView myview = ViewResolver(viewName);
		myview.render(view.getModel(), request, response);
	}

	private MyView ViewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String,String> paramMap = new HashMap<>();
		request.getParameterNames().asIterator()
			.forEachRemaining(paramName -> paramMap.put(paramName,request.getParameter(paramName)));
		return paramMap;
	}
}
