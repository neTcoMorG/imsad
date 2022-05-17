package jeiu.imsad.domain.partner;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryPartnerRepository implements PartnerRepository {

    private static List<Partner> store = new ArrayList<>();

    @Override
    public Long save(Partner partner) {
        store.add(partner);
        return partner.getId();
    }

    @Override
    public Partner findById(Long id) {
        for (Partner p : store) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Partner findByName(String name) {
        for (Partner p : store) {
            if (p.getCompany().equals(name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Partner findByPhone(String phone) {
        return null;
    }

    @Override
    public List<Partner> findAll() {
        return store;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void deleteByPhone(String phone) {

    }
}
