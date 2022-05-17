package jeiu.imsad.domain.partner;

import jeiu.imsad.domain.partner.Partner;
import jeiu.imsad.domain.partner.MemoryPartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final MemoryPartnerRepository repository;

    public void save (Partner partner) {
        validDuplicatePartner(partner);
        repository.save(partner);
    }

    public List<Partner> getPartners () {
        return repository.findAll();
    }

    public void validDuplicatePartner(Partner partner) {
        Partner find = repository.findByName(partner.getCompany());
        if (find != null) {
            throw new IllegalStateException("중복된 회사");
        }
    }
}
