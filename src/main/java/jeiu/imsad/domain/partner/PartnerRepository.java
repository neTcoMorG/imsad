package jeiu.imsad.domain.partner;

import jeiu.imsad.domain.partner.Partner;

import java.util.List;

public interface PartnerRepository {

    Long save(Partner partner);

    Partner findById(Long id);
    Partner findByName(String name);
    Partner findByPhone(String phone);
    List<Partner> findAll();

    void deleteById(Long id);
    void deleteByName(String name);
    void deleteByPhone(String phone);
}