import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Member;

@Controller
public class SessionEx {

    @GetMapping("/login")
    public String Login(Member member, HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("member",member);
        return "/member/loginok";
    }

    @GetMapping()
    public String Login1(Member member, HttpSession httpSession) {
        httpSession.setAttribute("member",member);
        return "/member/loginok";
    }

    @GetMapping()
    public String logout(Member member, HttpServletRequest httpServletRequest) {
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.invalidate();
        httpSession.removeAttribute("member");
        return "/member/logout";
    }

    @GetMapping()
    public String logout1(Member member, HttpSession httpSession) {
        httpSession.invalidate();
        return "/member/logout";
    }

}
