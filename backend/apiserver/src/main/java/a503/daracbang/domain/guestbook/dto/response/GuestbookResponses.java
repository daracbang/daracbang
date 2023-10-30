package a503.daracbang.domain.guestbook.dto.response;

import java.util.List;

public class GuestbookResponses {

    List<GuestbookResponse> guestbooks;

    public GuestbookResponses(List<GuestbookResponse> guestbooks) {
        this.guestbooks = guestbooks;
    }

    public List<GuestbookResponse> getGuestbooks() {
        return guestbooks;
    }
}
