package a503.daracbang.domain.guestbook.controller;

import a503.daracbang.domain.guestbook.dto.request.GuestbookCreateForm;
import a503.daracbang.domain.guestbook.dto.response.GuestbookResponses;
import a503.daracbang.domain.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import java.net.URI;

@RequestMapping("/api/guestbooks")
@RestController
@RequiredArgsConstructor
public class GuestbookController {

    private final GuestbookService guestbookService;

    @PostMapping("/{memberId}")
    public ResponseEntity<Void> create(@PathVariable("memberId") Long memberId, @RequestBody GuestbookCreateForm form) {
        guestbookService.save(memberId, form);
        return ResponseEntity.created(URI.create("/api/guestbook/" + memberId)).build();
    }

    @DeleteMapping("/{guestbookId}")
    public ResponseEntity<Void> delete(@PathVariable("guestbookId") Long guestbookId) {
        guestbookService.delete(guestbookId, 1L);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<GuestbookResponses> reads(@PathVariable("memberId") Long memberId,
                                      @PageableDefault Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        GuestbookResponses guestbooks = guestbookService.getGuestbooks(memberId, pageRequest);
        return ResponseEntity.ok(guestbooks);
    }
}
