package jeiu.imsad.domain;

import jeiu.imsad.domain.file.DriveManager;
import jeiu.imsad.domain.file.FileStore;
import jeiu.imsad.domain.partner.JpaPartnerRepository;
import jeiu.imsad.domain.partner.Partner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final JpaPartnerRepository repository;
    private final DriveManager driveManager;
    private final FileStore fileStore;

    private Partner admin;

    @PostConstruct
    public void init() {
        admin = new Partner(null, ADMIN_CONST.ADMIN_ID, "조영준", "01045228590", "ADMIN", "1234", "asd@naver.com");

        if (!driveManager.spaceIsExits(admin)) {
            driveManager.createPartnerSpace(admin.getCompanyName());
        }

        if (!repository.existsByLoginId(ADMIN_CONST.ADMIN_ID)) {
            repository.save(admin);
        }
    }
}
