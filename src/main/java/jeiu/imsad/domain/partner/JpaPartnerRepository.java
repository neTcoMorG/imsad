package jeiu.imsad.domain.partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPartnerRepository extends JpaRepository<Partner, Long> {
    Partner findByLoginId(String loginId);
    Partner findByCompanyName(String company);
    boolean existsByLoginId(String loginId);
}
