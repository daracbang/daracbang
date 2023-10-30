package a503.daracbang.domain.guestbook.service;

import a503.daracbang.domain.guestbook.dto.request.GuestbookCreateForm;
import a503.daracbang.domain.guestbook.dto.response.GuestbookResponse;
import a503.daracbang.domain.guestbook.dto.response.GuestbookResponses;
import a503.daracbang.domain.guestbook.entity.Guestbook;
import a503.daracbang.domain.guestbook.exception.GuestbookNotFoundException;
import a503.daracbang.domain.guestbook.exception.GuestbookNotWriterException;
import a503.daracbang.domain.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.List;

import static a503.daracbang.domain.guestbook.exception.GuestbookErrorCode.NOTFOUND_GUESTBOOK;
import static a503.daracbang.domain.guestbook.exception.GuestbookErrorCode.NOTWRITER_GUESTBOOK;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Transactional
    public void save(Long memberId, GuestbookCreateForm form) {
        guestbookRepository.save(form.toEntity(null));
    }

    @Transactional
    public void delete(Long guestbookId, Long memberId) {
        Guestbook guestbook = getGuestbook(guestbookId);
        if (!guestbook.isWriter(memberId)) {
            throw new GuestbookNotWriterException(NOTWRITER_GUESTBOOK);
        }
    }

    public Guestbook getGuestbook(Long guestbookId) {
        return guestbookRepository.findById(guestbookId)
            .orElseThrow(() -> new GuestbookNotFoundException(NOTFOUND_GUESTBOOK));
    }

    public GuestbookResponses getGuestbooks(Long memberId, Pageable pageable) {
        Page<GuestbookResponse> guestbooks = guestbookRepository.findAllGuestbookByPaging(memberId, pageable);
        return new GuestbookResponses(guestbooks.getContent());
    }
}
