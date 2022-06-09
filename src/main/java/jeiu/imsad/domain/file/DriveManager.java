package jeiu.imsad.domain.file;

import jeiu.imsad.domain.partner.Partner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DriveManager {

    @Value("${file.dir}")
    private String rootPath;

    public void createPartnerSpace(String companyName) {
        String fullPath = rootPath + companyName;
        File drive = new File(fullPath);
        if (!drive.exists()) { drive.mkdir(); }
    }

    public boolean spaceIsExits(Partner partner) {
        String fullPath = rootPath + partner.getCompanyName();
        File file = new File(fullPath);
        return file.exists();
    }

    public String getPartnerDrivePath (Partner partner) {
        return rootPath + partner.getCompanyName();
    }
}
