package a503.daracbang.domain.bgm.dto.response;

import lombok.Getter;

@Getter
public class BgmResponse {

    private String id;

    private String title;

    public BgmResponse(String id, String title) {
        this.id = id;
        this.title = title;
    }
}
