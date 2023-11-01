package a503.daracbang.domain.guestbook.dto.request;

import a503.daracbang.domain.guestbook.entity.Guestbook;
import a503.daracbang.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterGuestbookRequest {

    private String content;

    public RegisterGuestbookRequest(String content) {
        this.content = content;
    }

    public Guestbook toEntity(long memberId) {
        return new Guestbook(memberId, content);
    }
}
