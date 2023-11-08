package a503.daracbang.domain.bgm.dto.response;

import lombok.Getter;

@Getter
public class YoutubeResponse {

    private String id;

    private String title;

    public YoutubeResponse(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
