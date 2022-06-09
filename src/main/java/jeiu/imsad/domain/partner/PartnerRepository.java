package jeiu.imsad.domain.partner;

import java.util.List;

public interface PartnerRepository {

    void save(Partner partner);
    Partner findById(Long id);
    Partner findByLoginId(String loginId);
    Partner findByPhone(String phone);
    List<Partner> findAll();

    void deleteById(Long id);
    void deleteByName(String name);
    void deleteByPhone(String phone);
}
