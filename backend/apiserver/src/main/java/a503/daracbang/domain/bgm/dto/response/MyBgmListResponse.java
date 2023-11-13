package a503.daracbang.domain.bgm.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class MyBgmListResponse {

    private List<MyBgmResponse> bgms;

    public MyBgmListResponse(List<MyBgmResponse> bgms) {
        this.bgms = bgms;
    }
}
