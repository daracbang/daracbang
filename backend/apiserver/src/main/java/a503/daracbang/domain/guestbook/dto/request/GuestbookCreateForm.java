package a503.daracbang.domain.guestbook.dto.request;

import a503.daracbang.domain.guestbook.entity.Guestbook;
import a503.daracbang.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GuestbookCreateForm {

    private String content;

    public GuestbookCreateForm(String content) {
        this.content = content;
    }

    public Guestbook toEntity(Member member) {
        return new Guestbook(member, content);
    }
}
