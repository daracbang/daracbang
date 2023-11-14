package a503.daracbang.domain.bgm.dto.response;

import a503.daracbang.domain.bgm.entity.Bgm;
import lombok.Getter;

@Getter
public class MyBgmResponse {

    private Long bgmId;

    private String bgmName;

    private String url;

    public MyBgmResponse(Long bgmId, String bgmName,String url) {
        this.bgmId = bgmId;
        this.bgmName = bgmName;
        this.url = url;
    }

    public MyBgmResponse(Bgm bgm) {
        this.bgmId = bgm.getId();
        this.bgmName = bgm.getBgmName();
        this.url = bgm.getUrl();
    }
}
