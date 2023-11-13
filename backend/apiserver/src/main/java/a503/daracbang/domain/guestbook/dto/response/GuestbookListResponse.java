package a503.daracbang.domain.guestbook.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class GuestbookListResponse {

    List<GuestbookResponse> guestbooks;

    long lastId;

    public GuestbookListResponse(List<GuestbookResponse> guestbooks, long lastId) {
        this.guestbooks = guestbooks;
        this.lastId = lastId;
    }
}
