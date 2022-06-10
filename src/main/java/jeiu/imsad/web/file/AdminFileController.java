package jeiu.imsad.web.file;

import jeiu.imsad.domain.ADMIN_CONST;
import jeiu.imsad.domain.file.FileStore;
import jeiu.imsad.domain.file.JpaFileRepository;
import jeiu.imsad.domain.partner.JpaPartnerRepository;
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
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@Controller
@RequestMapping("/admin/file")
@RequiredArgsConstructor
public class AdminFileController {

    private final JpaPartnerRepository repository;
    private final JpaFileRepository fileRepository;
    private final FileStore store;


    @ResponseBody
    @GetMapping("/download/{company}/{fileUUID}")
    public ResponseEntity<Resource> fileDownloadController(@PathVariable String company,
                                                           @PathVariable("fileUUID") String fileName,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response) throws IOException {
        UrlResource urlResource;
        Partner partner = repository.findByCompanyName(company);

        if (store.isExits(partner, fileName)) {
            urlResource = new UrlResource("file:" + store.getFullPath(partner, fileName));

            String originalFileName = URLEncoder.encode(fileRepository.findByStoreFileName(fileName).
                    getUploadFileName(), "UTF-8");
            String contentDisposition = "attachment; filename=\"" + originalFileName + "\"";

            log.info("fileName={}", fileRepository.findByStoreFileName(fileName).getUploadFileName());

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(urlResource);
        }

        response.sendError(204, "없는 파일");
        return null;
    }
}

