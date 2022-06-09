package jeiu.imsad.domain.file;

import jeiu.imsad.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaFileRepository extends JpaRepository<UploadFile, Long> {

    List<UploadFile> findByUploader(Partner partner);
    UploadFile findByStoreFileName(String storeFileName);
}
