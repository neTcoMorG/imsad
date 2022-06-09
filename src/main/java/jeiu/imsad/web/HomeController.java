package jeiu.imsad.web;

import jeiu.imsad.domain.ADMIN_CONST;
import jeiu.imsad.domain.partner.Partner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request)  {
        HttpSession session = request.getSession();
        Partner login = (Partner) session.getAttribute("LOGIN");

        if (login != null) {
            if (login.getLoginId().equals(ADMIN_CONST.ADMIN_ID)) {
                return "redirect:/admin";
            }
            return "redirect:/my";
        }
        return "/login/loginForm";
    }
}
