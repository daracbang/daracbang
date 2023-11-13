package a503.daracbang.domain.guestbook.controller;

import a503.daracbang.domain.guestbook.dto.request.RegisterGuestbookRequest;
import a503.daracbang.domain.guestbook.dto.response.GuestbookListResponse;
import a503.daracbang.domain.guestbook.service.CreateGuestbookService;
import a503.daracbang.domain.guestbook.service.DeleteGuestbookService;
import a503.daracbang.domain.guestbook.service.FindGuestBookService;
import a503.daracbang.domain.member.util.MemberContextHolder;
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

    @PostMapping("/{ownerId}")
    public ResponseEntity<Void> create(@PathVariable("ownerId") Long ownerId,
                                       @Valid @RequestBody RegisterGuestbookRequest form) {
        Long writerId = MemberContextHolder.memberIdHolder.get();
        createGuestbookService.save(ownerId, writerId, form);
        return ResponseEntity.created(URI.create("/api/guestbook/" + ownerId)).build();
    }

    @DeleteMapping("/{guestbookId}")
    public ResponseEntity<Void> delete(@PathVariable("guestbookId") Long guestbookId) {
        Long writerId = MemberContextHolder.memberIdHolder.get();
        deleteGuestbookService.delete(guestbookId, writerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<GuestbookListResponse> reads(@PathVariable("ownerId") Long ownerId,
                                                       @RequestParam(value = "lastId", required = false) Long lastId) {
        GuestbookListResponse response;
        if (lastId == null || lastId < 1) {
            lastId = Long.MAX_VALUE;
        }
        response = findGuestBookService.getGuestBooks(ownerId, lastId);
        return ResponseEntity.ok(response);
    }
}
