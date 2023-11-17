package a503.daracbang.domain.bgm.controller;

import a503.daracbang.domain.bgm.dto.request.RegisterBgmIdRequest;
import a503.daracbang.domain.bgm.dto.request.RegisterBgmUrlRequest;
import a503.daracbang.domain.bgm.dto.response.MyBgmListResponse;
import a503.daracbang.domain.bgm.dto.response.YoutubeListResponse;
import a503.daracbang.domain.bgm.service.CreateBgmService;
import a503.daracbang.domain.bgm.service.DeleteBgmService;
import a503.daracbang.domain.bgm.service.FindBgmService;
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

@RequestMapping("/api/bgms")
@RestController
@RequiredArgsConstructor
public class BgmController {

    private final CreateBgmService createBgmService;
    private final DeleteBgmService deleteBgmService;
    private final FindBgmService findBgmService;

    @PostMapping("/url")
    public ResponseEntity<Void> createByUrl(@Valid @RequestBody RegisterBgmUrlRequest registerBgmUrlRequest) {
        Long memberId = MemberContextHolder.memberIdHolder.get();
        createBgmService.saveBgmUrl(registerBgmUrlRequest, memberId);
        return ResponseEntity.created(URI.create("/api/bgm/url")).build();
    }

    @PostMapping
    public ResponseEntity<Void> createById(@Valid @RequestBody RegisterBgmIdRequest registerBgmIdRequest) {
        Long memberId = MemberContextHolder.memberIdHolder.get();
        createBgmService.saveBgmId(registerBgmIdRequest, memberId);
        return ResponseEntity.created(URI.create("/api/bgm")).build();
    }

    @GetMapping("/search")
    public ResponseEntity<YoutubeListResponse> searchYoutube(@RequestParam("q") String q) {
        if (q == null)
            return ResponseEntity.badRequest().build();
        YoutubeListResponse bgms = findBgmService.findYoutube(q);
        return ResponseEntity.ok(bgms);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MyBgmListResponse> getMyBgms(@PathVariable("memberId") Long memberId) {
        MyBgmListResponse myBgms = findBgmService.getMyBgms(memberId);
        return ResponseEntity.ok(myBgms);
    }

    @DeleteMapping("/{bgmId}")
    public ResponseEntity<Void> delete(@PathVariable("bgmId") Long bgmId) {
        Long memberId = MemberContextHolder.memberIdHolder.get();
        deleteBgmService.delete(bgmId, memberId);
        return ResponseEntity.noContent().build();
    }
}
