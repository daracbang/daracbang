package a503.daracbang.domain.guestbook.dto.request;

import a503.daracbang.domain.guestbook.entity.Guestbook;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestbookCreateForm {

    private String content;

    public GuestbookCreateForm(String content) {
        this.content = content;
    }

    public Guestbook toEntity(Long memberId) {
        return new Guestbook(memberId, content);
    }
}
