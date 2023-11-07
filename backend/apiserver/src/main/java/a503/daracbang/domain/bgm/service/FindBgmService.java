package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.dto.response.BgmListResponse;
import a503.daracbang.domain.bgm.dto.response.BgmResponse;
import a503.daracbang.domain.bgm.repository.BgmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FindBgmService {

    private final BgmRepository bgmRepository;
    private final YoutubeApiService youtubeApiService;

    public BgmListResponse findMusic(String q) {
        return youtubeApiService.findMusic(q);
    }
}
