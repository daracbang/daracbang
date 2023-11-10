package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.entity.Bgm;
import a503.daracbang.domain.bgm.repository.BgmRepository;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DeleteBgmService {

    private final BgmRepository bgmRepository;

    private final MemberRepository memberRepository;

    private final FindBgmService findBgmService;

    /**
     * Member 연동이 완료되면 세부 구현
     */
    @Transactional
    public void delete(Long bgmId, Long memberId) {
        Optional<Member> optionalMember = memberRepository.findById(memberId);
        if (optionalMember.isEmpty()) {
            return;
        }
        Member member = optionalMember.get();
        Bgm bgm = findBgmService.findBgm(bgmId);
        bgmRepository.delete(bgm);
    }
}
