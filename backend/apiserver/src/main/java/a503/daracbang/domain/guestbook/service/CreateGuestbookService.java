package a503.daracbang.domain.guestbook.service;

import a503.daracbang.domain.guestbook.dto.request.RegisterGuestbookRequest;
import a503.daracbang.domain.guestbook.repository.GuestbookRepository;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CreateGuestbookService {

    private final GuestbookRepository guestbookRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public void save(Long memberId, RegisterGuestbookRequest form) {
        Optional<Member> member = memberRepository.findById(memberId);
        guestbookRepository.save(form.toEntity(member.get()));
    }
}
