package hello.servlet.web.springmvc.v3;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
	private final MemberRepository memberRepository = MemberRepository.getInstance();

	@RequestMapping(value="/new-form", method= RequestMethod.GET)
	//@GetMapping("/new-form") 이랑 같음
	public String process() {
		System.out.println("SpringMemberControllerV2.process");
		return "new-form";
	}

	@RequestMapping(value="/save", method = RequestMethod.POST)
	//@PostMapping("/save") 이랑 같음
	public String process(@RequestParam("username") String username,
								@RequestParam("age") int age, Model model) {
		Member member = new Member(username, age);
		memberRepository.save(member);
		model.addAttribute("member", member);
		return "save-result";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String List(Model model) {
		List<Member> members = memberRepository.findAll();
		model.addAttribute("members", members);
		return "members";
	}
}
