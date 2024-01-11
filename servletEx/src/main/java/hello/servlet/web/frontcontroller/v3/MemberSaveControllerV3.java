package hello.servlet.web.frontcontroller.v3;

import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;

public class MemberSaveControllerV3 implements ControllerV3{
	private MemberRepository repository = MemberRepository.getInstance();
	@Override
	public ModelView process(Map<String, String> paramMap) {
		String usernmae = paramMap.get("username");
		int age = Integer.parseInt(paramMap.get("age"));

		Member member = new Member(usernmae, age);
		repository.save(member);

		ModelView mv = new ModelView("save-result");
		mv.getModel().put("member",member);
		return mv;
	}
}
