package hello.servlet.web.frontcontroller.v4.controller;

import java.util.Map;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

public class MemberFormControllerV4 implements ControllerV4 {

	/**
	 * @param paramMap
	 * @param model
	 * @return viewName
	 */
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		return "new-form";
	}
}
