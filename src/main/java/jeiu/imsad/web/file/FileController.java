package jeiu.imsad.web.file;

import jeiu.imsad.domain.ADMIN_CONST;
import jeiu.imsad.domain.file.DriveManager;
import jeiu.imsad.domain.file.FileStore;
import jeiu.imsad.domain.file.JpaFileRepository;
import jeiu.imsad.domain.partner.Partner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileStore store;
    private final JpaFileRepository fileRepository;
    private final DriveManager driveManager;

    @PostMapping
    public String upload(@RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        Partner partner = (Partner) session.getAttribute("LOGIN");
        String referURL = request.getHeader("Referer");

        store.saveFile(file, partner);

        if (partner.getLoginId().equals(ADMIN_CONST.ADMIN_ID)) {
            return "redirect:/admin?companyId=" + partner.getId();
        }

        return "redirect:" + referURL;
    }

    @ResponseBody
    @GetMapping("/download/{fileUUID}")
    public ResponseEntity<Resource> fileDownloadController(@PathVariable String fileUUID,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) throws IOException {
        Partner partner = (Partner) request.getSession(false).getAttribute("LOGIN");
        UrlResource urlResource;

        if (store.isExits(partner, fileUUID)) {
            urlResource = new UrlResource("file:" + store.getFullPath(partner, fileUUID));
            String contentDisposition = "attachment; filename=\"" + fileUUID + "\"";
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(urlResource);
        }

        response.sendError(204, "없는 파일");
        return null;
    }

    @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable Long fileId,
                             HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        Partner loginPartner = (Partner) request.getSession().getAttribute("LOGIN");
        String referURL = request.getHeader("Referer");

        try {
            store.deleteFile(loginPartner, fileId);
        }
        catch(FileNotFoundException e) {
            response.sendError(204);
        }
        fileRepository.deleteById(fileId);
        return "redirect:" + referURL;
    }
}
