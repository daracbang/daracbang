package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.dto.request.RegisterBgmIdRequest;
import a503.daracbang.domain.bgm.dto.request.RegisterBgmUrlRequest;
import a503.daracbang.domain.bgm.repository.BgmRepository;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.repository.MemberRepository;
import a503.daracbang.domain.member.service.GetMemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CreateBgmService {

    private final BgmRepository bgmRepository;

    private final GetMemberInfoService getMemberService;

    @Transactional
    public void saveBgmUrl(RegisterBgmUrlRequest registerBgmUrlRequest, long memberId) {
        Member member = getMemberService.getMember(memberId);
        bgmRepository.save(registerBgmUrlRequest.toEntity(member));
    }

    @Transactional
    public void saveBgmId(RegisterBgmIdRequest registerBgmIdRequest, long memberId) {
        Member member = getMemberService.getMember(memberId);
        bgmRepository.save(registerBgmIdRequest.toEntity(member));
    }
}
