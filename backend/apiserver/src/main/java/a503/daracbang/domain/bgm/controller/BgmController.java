package a503.daracbang.domain.bgm.controller;

import a503.daracbang.domain.bgm.dto.request.RegisterBgmRequest;
import a503.daracbang.domain.bgm.dto.response.BgmListResponse;
import a503.daracbang.domain.bgm.service.CreateBgmService;
import a503.daracbang.domain.bgm.service.DeleteBgmService;
import a503.daracbang.domain.bgm.service.FindBgmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/api/bgm")
@RestController
@RequiredArgsConstructor
public class BgmController {

    private final CreateBgmService createBgmService;
    private final DeleteBgmService deleteBgmService;
    private final FindBgmService findBgmService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody RegisterBgmRequest registerBgmRequest) {
        createBgmService.saveBgm(registerBgmRequest);
        return ResponseEntity.created(URI.create("/api/bgm")).build();
    }

    @GetMapping("/search")
    public ResponseEntity<BgmListResponse> search(@RequestParam("q") String q) {
        if(q == null)
            return ResponseEntity.badRequest().build();
        BgmListResponse bgms = findBgmService.findMusic(q);
        return ResponseEntity.ok(bgms);
    }
}
