package jeiu.imsad.domain.file;

import jeiu.imsad.domain.partner.Partner;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemoryFileRepository {

    private static Map<Long, UploadFile> store = new HashMap<>();
    private static Long sequence = 0L;

    public void save(UploadFile uploadFile) {
        ++sequence;
        store.put(sequence, uploadFile);
    }

    public String findOriginalNameByStoreName(String storeName) {
        List<UploadFile> files = new ArrayList<>(store.values());
        for (UploadFile file : files) {
            if (file.getStoreFileName().equals(storeName)) {
                return file.getUploadFileName();
            }
        }
        return null;
    }

    public List<UploadFile> findByCompany(String company) {
        List<UploadFile> result = new ArrayList<>();
        store.entrySet().stream().forEach(file -> {
            if (file.getValue().getUploader().getCompanyName().equals(company)) {
                result.add(file.getValue());
            }
        });
        return result;
    }

    public List<UploadFile> findByPartner(Partner partner) {
        List<UploadFile> result = new ArrayList<>();
        store.entrySet().stream().forEach(file -> {
            if (file.getValue().getUploader().getCompanyName().equals(partner.getCompanyName())) {
                result.add(file.getValue());
            }
        });
        return result;
    }

    public List<UploadFile> findAll() {
        return new ArrayList<>(store.values());
    }
}
