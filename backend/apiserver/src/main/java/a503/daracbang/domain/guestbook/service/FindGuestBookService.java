package a503.daracbang.domain.guestbook.service;

import a503.daracbang.domain.guestbook.dto.response.GuestbookListResponse;
import a503.daracbang.domain.guestbook.dto.response.GuestbookResponse;
import a503.daracbang.domain.guestbook.entity.Guestbook;
import a503.daracbang.domain.guestbook.exception.GuestbookNotFoundException;
import a503.daracbang.domain.guestbook.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static a503.daracbang.domain.guestbook.exception.GuestbookErrorCode.NOTFOUND_GUESTBOOK;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FindGuestBookService {

    private final GuestbookRepository guestbookRepository;

    private final int PAGE_SIZE = 15;

    public Guestbook getGuestbook(Long guestbookId) {
        return guestbookRepository.findById(guestbookId)
            .orElseThrow(() -> new GuestbookNotFoundException(NOTFOUND_GUESTBOOK));
    }

    public GuestbookListResponse getGuestbooks(Long memberId, Integer lastId) {
        PageRequest pageRequest  = PageRequest.of(0, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id"));
        int nextId = lastId + PAGE_SIZE;
//        Member Entity가 구현되지 않아 null 및 주석처리
//        List<GuestbookResponse> guestbooks = guestbookRepository.findAllGuestbookByPaging(memberId, nextId, pageRequest);
        return new GuestbookListResponse(null);
    }
}
