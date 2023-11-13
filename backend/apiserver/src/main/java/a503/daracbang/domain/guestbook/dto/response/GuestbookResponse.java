package a503.daracbang.domain.guestbook.dto.response;

import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GuestbookResponse {

    private Long guestBookId;

    private Long writerId;

    private String nickname;

    private String profileImage;

    @Size(min = 1, max = 200, message = "1 ~ 200 자까지 가능합니다.")
    private String content;

    private LocalDateTime createdAt;

    public GuestbookResponse(Long guestBookId, Long writerId, String nickname, String profileImage, String content, LocalDateTime createdAt) {
        this.guestBookId = guestBookId;
        this.writerId = writerId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.content = content;
        this.createdAt = createdAt;
    }
}
