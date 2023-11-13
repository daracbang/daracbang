package a503.daracbang.domain.guestbook.service;

import a503.daracbang.domain.guestbook.dto.request.RegisterGuestbookRequest;
import a503.daracbang.domain.guestbook.repository.GuestbookRepository;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.service.GetMemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CreateGuestbookService {

    private final GuestbookRepository guestbookRepository;

    private final GetMemberInfoService getMemberInfoService;

    @Transactional
    public void save(Long memberId, RegisterGuestbookRequest form) {
        Member member = getMemberInfoService.getMember(memberId);
        guestbookRepository.save(form.toEntity(member));
    }
}
