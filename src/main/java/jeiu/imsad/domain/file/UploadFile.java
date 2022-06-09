package jeiu.imsad.domain.file;


import jeiu.imsad.domain.partner.Partner;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UploadFile {

    public UploadFile() {}

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "UPLOADER")
    private Partner uploader;

    private String uploadFileName;
    private String storeFileName;

}
