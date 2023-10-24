package a503.daracbang.domain.guestbook.service;

import a503.daracbang.domain.guestbook.dto.request.GuestbookCreateForm;
import a503.daracbang.domain.guestbook.entity.Guestbook;
import a503.daracbang.domain.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Transactional
    public void save(Long memberId, GuestbookCreateForm form) {
        guestbookRepository.save(form.toEntity(memberId));
    }
}
