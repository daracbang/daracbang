package a503.daracbang.domain.guestbook.dto.response;

import lombok.Getter;

@Getter
public class GuestbookResponse {

    private Long memberId;

    private String nickname;

    private String profileImage;

    private String content;

    public GuestbookResponse(Long memberId, String nickname, String profileImage, String content) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.content = content;
    }
}
