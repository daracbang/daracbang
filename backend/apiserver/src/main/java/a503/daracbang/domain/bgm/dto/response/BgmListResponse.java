package a503.daracbang.domain.bgm.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class BgmListResponse {

    private final List<BgmResponse> bgms;

    public BgmListResponse(List<BgmResponse> bgms) {
        this.bgms = bgms;
    }
}
