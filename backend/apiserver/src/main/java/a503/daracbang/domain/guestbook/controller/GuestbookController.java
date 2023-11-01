package a503.daracbang.domain.guestbook.controller;

import a503.daracbang.domain.guestbook.dto.request.RegisterGuestbookRequest;
import a503.daracbang.domain.guestbook.dto.response.GuestbookListResponse;
import a503.daracbang.domain.guestbook.service.CreateGuestbookService;
import a503.daracbang.domain.guestbook.service.DeleteGuestbookService;
import a503.daracbang.domain.guestbook.service.FindGuestBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/api/guestbooks")
@RestController
@RequiredArgsConstructor
public class GuestbookController {

    private final CreateGuestbookService createGuestbookService;
    private final DeleteGuestbookService deleteGuestbookService;
    private final FindGuestBookService findGuestBookService;

    private final int PAGE_SIZE = 15;

    @PostMapping("/{memberId}")
    public ResponseEntity<Void> create(@PathVariable("memberId") Long memberId,
                                       @Valid @RequestBody RegisterGuestbookRequest form) {
        createGuestbookService.save(memberId, form);
        return ResponseEntity.created(URI.create("/api/guestbook/" + memberId)).build();
    }

    @DeleteMapping("/{guestbookId}")
    public ResponseEntity<Void> delete(@PathVariable("guestbookId") Long guestbookId) {
        deleteGuestbookService.delete(guestbookId, 1L);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GuestbookListResponse> reads(@PathVariable("memberId") Long memberId,
                                                       @RequestParam(value = "lastId", required = false) Integer lastId) {
        if (lastId == null) {
            lastId = 0;
        }
        GuestbookListResponse guestbooks = findGuestBookService.getGuestbooks(memberId, lastId);
        return ResponseEntity.ok(guestbooks);
    }
}
