package a503.daracbang.domain.bgm.dto.response;

import a503.daracbang.domain.bgm.entity.Bgm;
import lombok.Getter;

@Getter
public class MyBgmResponse {

    private Long bgmId;

    private String bgmName;

    private String videoId;

    private String url;

    public MyBgmResponse(Long bgmId, String bgmName, String videoId, String url) {
        this.bgmId = bgmId;
        this.bgmName = bgmName;
        this.videoId = videoId;
        this.url = url;
    }

    public MyBgmResponse(Bgm bgm) {
        this.bgmId = bgm.getId();
        this.bgmName = bgm.getBgmName();
        this.videoId = bgm.getVideoId();
        this.url = bgm.getUrl();
    }
}
