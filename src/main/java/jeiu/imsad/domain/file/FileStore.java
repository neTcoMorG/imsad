package jeiu.imsad.domain.file;

import jeiu.imsad.domain.partner.Partner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FileStore {

    private final JpaFileRepository jpaFileRepository;
    private final DriveManager driveManager;

    @Value("${file.dir}")
    private String fileDir;

    public boolean isExits(Partner partner, String fileName) {
        File file = new File(fileDir + partner.getCompanyName() + "/" + fileName);
        return file.exists();
    }

    public String getFullPath(Partner partner, String fileName) {
        return fileDir + partner.getCompanyName() + "/" + fileName;
    }

    public void deleteFile(Partner uploader, Long fileId) throws FileNotFoundException {
        String partnerSpacePath = driveManager.getPartnerDrivePath(uploader);
        String targetFileName = jpaFileRepository.findById(fileId).get().getStoreFileName();
        File target = new File(partnerSpacePath + "/" + targetFileName);

        if (target.exists()) {
            target.delete();
            return;
        }
        throw new FileNotFoundException();
    }

    public UploadFile saveFile(MultipartFile file, Partner uploader) throws IOException {
        if (file.isEmpty()) { return null; }

        String storeFileName = createStoreFileName(file.getOriginalFilename());
        UploadFile uploadFile = new UploadFile();
        uploadFile.setUploadFileName(file.getOriginalFilename());
        uploadFile.setStoreFileName(storeFileName);
        uploadFile.setUploader(uploader);

        file.transferTo(new File(getFullPath(uploader, storeFileName)));
        jpaFileRepository.save(uploadFile);
        return uploadFile;
    }

    private String createStoreFileName(String originalFileName) {
        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFileName);
        return uuid + "." + ext;
    }

    private String extractExt(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1);
    }
}
