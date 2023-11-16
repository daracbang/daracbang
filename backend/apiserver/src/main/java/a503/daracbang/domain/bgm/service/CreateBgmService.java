package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.dto.request.RegisterBgmRequest;
import a503.daracbang.domain.bgm.repository.BgmRepository;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.service.GetMemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CreateBgmService {

    private final BgmRepository bgmRepository;

    private final GetMemberInfoService getMemberService;

    @Transactional
    public void saveBgm(RegisterBgmRequest registerBgmRequest, long memberId) {
        Member member = getMemberService.getMember(memberId);
        bgmRepository.save(registerBgmRequest.toEntity(member));
    }
}
