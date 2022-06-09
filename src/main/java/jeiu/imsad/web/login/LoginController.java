package jeiu.imsad.web.login;

import jeiu.imsad.domain.login.LoginService;
import jeiu.imsad.domain.partner.JpaPartnerRepository;
import jeiu.imsad.domain.partner.Partner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JpaPartnerRepository repository;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(defaultValue = "/") String redirectURL) {
        //필드 검증
        if (bindingResult.hasErrors()) {
            return "/login/loginForm";
        }
        //계정이 없다면
        if (loginService.login(convertToPartner(form)) == null) {
            bindingResult.reject(null, null, "해당 사용자를 찾을 수 없습니다.");
            return "/login/loginForm";
        }

        //로그인 성공
        Partner partner = repository.findByLoginId(form.getLoginId());
        
        //세션 생성 및 값 설정
        HttpSession session = request.getSession();
        session.setAttribute("LOGIN", partner);
        return "redirect:" + redirectURL;
    }

    public static Partner convertToPartner(LoginForm form) {
        Partner partner = new Partner();
        partner.setLoginId(form.getLoginId());
        partner.setPassword(form.getPassword());
        return partner;
    }
}
