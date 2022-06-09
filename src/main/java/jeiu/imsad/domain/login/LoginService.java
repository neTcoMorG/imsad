package jeiu.imsad.domain.login;

import jeiu.imsad.domain.partner.JpaPartnerRepository;
import jeiu.imsad.domain.partner.Partner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JpaPartnerRepository repository;

    public void save(Partner partner) {
        repository.save(partner);
    }

    public Partner login(Partner partner) {
        Partner loginMember = repository.findByLoginId(partner.getLoginId());
        if (loginMember != null && loginMember.getPassword().equals(partner.getPassword())) {
            return loginMember;
        }
        return null;
    }
}
