package a503.daracbang.domain.bgm.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class YoutubeListResponse {

    private final List<YoutubeResponse> bgms;

    public YoutubeListResponse(List<YoutubeResponse> bgms) {
        this.bgms = bgms;
    }
}
