package hello.servlet.web.frontcontroller.v5.adapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.ViewResolver;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

	//아무 컨트롤러나 다 지원하기 위해서 Object 값을 value에 넣었다
	private final Map<String,Object> handlerMappingMap = new HashMap<String,Object>();
	private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<MyHandlerAdapter>();

	public FrontControllerServletV5() {
		initHandlerMappingMap();
		initHandlerAdapters();
	}

	private void initHandlerMappingMap() {
		handlerMappingMap.put("/front-controller/v5/v3/members/new-form" , new MemberFormControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members/save" , new MemberSaveControllerV3());
		handlerMappingMap.put("/front-controller/v5/v3/members" , new MemberListControllerV3());
	}

	private void initHandlerAdapters() {
		handlerAdapters.add(new ControllerV3HandlerAdapter());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object handler = getHandler(request);
		if(handler==null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		MyHandlerAdapter adapter = getHandlerAdapter(handler);

		ModelView mv = adapter.handle(request,response,handler);

		String viewName = mv.getViewName();
		MyView view = ViewResolver(viewName);
		view.render(mv.getModel(), request, response);

	}

	//핸들러 찾아오기
	private Object getHandler(HttpServletRequest request) {
		String requestURI =  request.getRequestURI();
		return handlerMappingMap.get(requestURI);
	}

	//핸들러 어댑터 찾아오기
	private MyHandlerAdapter getHandlerAdapter(Object handler) {
		for (MyHandlerAdapter adapter : handlerAdapters) {
			if(adapter.supports(handler)) {
				return adapter;
			}
		}
		throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다");
	}

	private MyView ViewResolver(String viewName) {
		return new MyView("/WEB-INF/views/" + viewName + ".jsp");
	}
}
