package a503.daracbang.domain.guestbook.service;

import a503.daracbang.domain.guestbook.dto.request.RegisterGuestbookRequest;
import a503.daracbang.domain.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CreateGuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Transactional
    public void save(Long memberId, RegisterGuestbookRequest form) {
//         현재 Member 정보가 다 구현되지 않아 1L 로 처리
         guestbookRepository.save(form.toEntity(1L));
    }
}
