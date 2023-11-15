package a503.daracbang.domain.bgm.dto.response;

import lombok.Getter;

@Getter
public class YoutubeResponse {

    private String videoId;

    private String title;

    public YoutubeResponse(String videoId, String title) {
        this.videoId = videoId;
        this.title = title;
    }
}
