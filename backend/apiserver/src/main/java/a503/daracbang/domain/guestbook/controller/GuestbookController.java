package a503.daracbang.domain.guestbook.controller;

import a503.daracbang.domain.guestbook.dto.request.GuestbookCreateForm;
import a503.daracbang.domain.guestbook.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
