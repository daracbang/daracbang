package a503.daracbang.domain.guestbook.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class GuestbookListResponse {

    List<GuestbookResponse> datas;

    long lastId;

    public GuestbookListResponse(List<GuestbookResponse> guestbooks, long lastId) {
        this.datas = guestbooks;
        this.lastId = lastId;
    }
}
