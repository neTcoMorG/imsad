package jeiu.imsad.domain;

import jeiu.imsad.domain.member.Member;
import jeiu.imsad.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member1 = new Member();
        member1.setLoginId("test");
        member1.setPassword("test!");
        member1.setName("joyoungjun");

        memberRepository.save(member1);
    }
}
