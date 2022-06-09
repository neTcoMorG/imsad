package jeiu.imsad.domain.partner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final JpaPartnerRepository repository;

    public List<Partner> getPartners() {
        return repository.findAll();
    }

    public void save(Partner partner) {
        repository.save(partner);
    }

    public boolean validDuplicatePartner(Partner partner) {
        return false;
    }

    public Partner findById(Long id) {
        return repository.findById(id).get();
    }
}
