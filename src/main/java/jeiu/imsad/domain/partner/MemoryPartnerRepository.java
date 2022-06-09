package jeiu.imsad.domain.partner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class MemoryPartnerRepository implements PartnerRepository {

    private static Map<Long, Partner> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public void save(Partner partner) {
        partner.setId(++sequence);
        store.put(partner.getId(), partner);
        log.info("create partnerId={}", sequence);
    }

    @Override
    public Partner findById(Long id) {
        return store.get(id);
    }

    public Partner findByCompany(String company) {
        Partner p;
        for (Long aLong : store.keySet()) {
            p = store.get(aLong);
            if (p.getCompanyName().equals(company)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Partner findByLoginId(String loginId) {
        Partner p;
        for (Long aLong : store.keySet()) {
            p = store.get(aLong);
            if (p.getLoginId().equals(loginId)) {
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
        return new ArrayList<>(store.values());
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
