package jeiu.imsad.controller;

import jeiu.imsad.domain.Partner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/add")
public class PartnerController {

    // /add 주소로 접근 시 페이지 리턴
    @GetMapping
    public String addPage() {
        return "add/addPartner";
    }

    // 파트너 생성
    @PostMapping
    public String addPartner(@ModelAttribute Partner partner) {
        return "redirect:/";
    }
}
