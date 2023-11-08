package a503.daracbang.domain.guestbook.service;

import a503.daracbang.domain.guestbook.entity.Guestbook;
import a503.daracbang.domain.guestbook.exception.GuestbookNotWriterException;
import a503.daracbang.domain.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static a503.daracbang.domain.guestbook.exception.GuestbookErrorCode.NOTWRITER_GUESTBOOK;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DeleteGuestbookService {

    private final GuestbookRepository guestbookRepository;

    private final FindGuestBookService findGuestBookService;

    @Transactional
    public void delete(Long guestbookId, Long memberId) {
//        Member 엔티티가 구현되지 않아 주석처리
        Guestbook guestbook = findGuestBookService.getGuestbook(guestbookId);
        if (!guestbook.isWriter(memberId)) {
            throw new GuestbookNotWriterException(NOTWRITER_GUESTBOOK);
        }
    }
}
