package jeiu.imsad.web.partner;

import jeiu.imsad.domain.file.JpaFileRepository;
import jeiu.imsad.domain.file.MemoryFileRepository;
import jeiu.imsad.domain.partner.Partner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/my")
@RequiredArgsConstructor
public class PartnerController {

    private final JpaFileRepository fileRepository;

    @GetMapping
    public String home(Model model, HttpServletRequest request) {
        Partner partner = (Partner) request.getSession(false).getAttribute("LOGIN");
        model.addAttribute("files", fileRepository.findByUploader(partner));
        model.addAttribute("company_Id", partner.getId());
        return "/test/upload-form";
    }
}
