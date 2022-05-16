package jeiu.imsad.controller;

import jeiu.imsad.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WelcomeController {

    private final PartnerService service;

    @GetMapping("/")
    public String index (Model model) {
        model.addAttribute("partners", service.getPartners());
        return "index";
    }
}
