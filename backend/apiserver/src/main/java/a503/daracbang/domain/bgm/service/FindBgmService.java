package a503.daracbang.domain.bgm.service;

import a503.daracbang.domain.bgm.dto.response.MyBgmListResponse;
import a503.daracbang.domain.bgm.dto.response.MyBgmResponse;
import a503.daracbang.domain.bgm.dto.response.YoutubeListResponse;
import a503.daracbang.domain.bgm.entity.Bgm;
import a503.daracbang.domain.bgm.repository.BgmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FindBgmService {

    private final BgmRepository bgmRepository;
    private final YoutubeApiService youtubeApiService;

    public YoutubeListResponse findYoutube(String q) {
        return youtubeApiService.findMusic(q);
    }

    public Bgm findBgm(Long bgmId) {
        return bgmRepository.findById(bgmId)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 bgm 입니다."));
    }

    public MyBgmListResponse getMyBgms(Long memberId) {
        List<MyBgmResponse> bgms = bgmRepository.findAllByMemberId(memberId);
        return new MyBgmListResponse(bgms);
    }
}
