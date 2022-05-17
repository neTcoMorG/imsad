package jeiu.imsad.web.partner;

import jeiu.imsad.domain.partner.Partner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/잠시테스트하려고없앰")
public class PartnerController {

    /* /add 주소로 접근 시 페이지 리턴 */
    @GetMapping
    public String addPage() {
        return "add/index";
    }

    /* 파트너 생성 */
    @PostMapping
    public String addPartner(@Valid @ModelAttribute Partner partner, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            model.addAttribute("errors", bindingResult);
            return "add/index";
        }
        return "redirect:/";
    }
}
