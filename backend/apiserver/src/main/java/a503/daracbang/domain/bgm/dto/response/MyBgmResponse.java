package a503.daracbang.domain.bgm.dto.response;

import a503.daracbang.domain.bgm.entity.Bgm;
import lombok.Getter;

@Getter
public class MyBgmResponse {

    private Long bgmId;

    private String bgmName;

    private String videoId;

    public MyBgmResponse(Long bgmId, String bgmName,String videoId) {
        this.bgmId = bgmId;
        this.bgmName = bgmName;
        this.videoId = videoId;
    }

    public MyBgmResponse(Bgm bgm) {
        this.bgmId = bgm.getId();
        this.bgmName = bgm.getBgmName();
        this.videoId = bgm.getVideoId();
    }
}
