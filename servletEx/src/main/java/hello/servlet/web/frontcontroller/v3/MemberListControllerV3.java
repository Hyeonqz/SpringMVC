package hello.servlet.web.frontcontroller.v3;

import java.util.List;
import java.util.Map;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;

public class MemberListControllerV3 implements ControllerV3{
	private MemberRepository repository = MemberRepository.getInstance();
	@Override
	public ModelView process(Map<String, String> paramMap) {
		List<Member> members = repository.findAll();
		ModelView mv = new ModelView("members");
		mv.getModel().put("members",members);
		return mv;
	}
}
