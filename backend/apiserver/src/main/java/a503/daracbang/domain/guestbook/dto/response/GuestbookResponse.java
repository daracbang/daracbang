package a503.daracbang.domain.guestbook.dto.response;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class GuestbookResponse {

    private Long memberId;

    private String nickname;

    private String profileImage;

    @Size(min = 1, max = 200, message = "1 ~ 200 자까지 가능합니다.")
    private String content;

    public GuestbookResponse(Long memberId, String nickname, String profileImage, String content) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.content = content;
    }
}
