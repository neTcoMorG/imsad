package jeiu.imsad.web.admin;

import jeiu.imsad.domain.ADMIN_CONST;
import jeiu.imsad.domain.file.JpaFileRepository;
import jeiu.imsad.domain.file.MemoryFileRepository;
import jeiu.imsad.domain.partner.Partner;
import jeiu.imsad.domain.partner.PartnerRepository;
import jeiu.imsad.domain.partner.PartnerService;
import jeiu.imsad.domain.file.DriveManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final PartnerService partnerService;
    private final JpaFileRepository fileRepository;
    private final DriveManager driveManager;

    @Value("${file.dir}")
    private String rootPath;

    @GetMapping("/my")
    public String adminSpace(Model model) {
        return "/admin/home";
    }

    @GetMapping
    public String adminHome(Model model, @RequestParam(required = false) String companyId,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        model.addAttribute("partners", partnerService.getPartners()); // 리스트에 협력사들 넘김
        if (companyId != null) {
            log.info("companyId={}", companyId);
            Partner partner = partnerService.findById(Long.parseLong(companyId));
            model.addAttribute("select", fileRepository.findByUploader(partner));
            return "/admin/home";
        }

        return "/admin/home";
    }
    
    @GetMapping("/addPartner")
    public String addPartnerForm(@ModelAttribute("partnerForm") SavePartnerForm form) {
        return "/admin/addPartnerForm";
    }

    @PostMapping("/addPartner")
    public String addPartner(@Valid @ModelAttribute("partnerForm") SavePartnerForm form, BindingResult bindingResult) {
        // 입력 폼 검증
        if (bindingResult.hasErrors()) {
            return "/admin/addPartnerForm";
        }
        
        //비밀번호 확인 검증
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            bindingResult.reject("", "비밀번호가 서로 일치하지 않습니다.");
            return "/admin/addPartnerForm";
        }

        //검증 통과
        partnerService.save(convertToPartner(form));    // form을 Partner 객체로 변환 후 계정 생성
        driveManager.createPartnerSpace(form.getCompanyName());
        log.info("create Partner={}", form);
        return "redirect:/";
    }

    public static Partner convertToPartner(SavePartnerForm form) {
        Partner partner = new Partner();
        partner.setCompanyName(form.getCompanyName());
        partner.setName(form.getCompanyName());
        partner.setLoginId(form.getLoginId());
        partner.setPhone(form.getPhone());
        partner.setEmail(form.getEmail());
        partner.setPassword(form.getConfirmPassword());
        return partner;
    }
}
