package a503.daracbang.domain.guestbook.dto.response;

import java.util.List;

public class GuestbookListResponse {

    List<GuestbookResponse> guestbooks;

    public GuestbookListResponse(List<GuestbookResponse> guestbooks) {
        this.guestbooks = guestbooks;
    }

    public List<GuestbookResponse> getGuestbooks() {
        return guestbooks;
    }
}
