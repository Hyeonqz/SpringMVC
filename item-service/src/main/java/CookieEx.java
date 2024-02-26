import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CookieEx {

    @GetMapping()
    public String mailMain(Mall mall, HttpServletResponse httpServletResponse) {
        Cookie cookie = new Cookie("gender", mall.getGender());

        if(mall.isCookieDel()) {
            cookie.setMaxAge(0);
            mall.setGender(null);
        } else {
            cookie.setMaxAge(60*60*24*30);
        }
        httpServletResponse.addCookie(cookie);
        return "/mall/main";
    }

    @GetMapping()
    public String mallIndex(Mall,mall, @CookieValue(value="gender", required = false) Cookie cookie) {
        if(cookie!=null) {
            mall.setGender(cookie.getValue());

            return "/mall/main";
        }
    }
}
