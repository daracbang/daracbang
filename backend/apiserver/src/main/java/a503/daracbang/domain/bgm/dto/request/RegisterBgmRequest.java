package a503.daracbang.domain.bgm.dto.request;

import a503.daracbang.domain.bgm.entity.Bgm;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterBgmRequest {

    @Size(min = 1, max = 100, message = "1 ~ 100 자 내로 입력해주세요.")
    private String musicName;

    @Size(min = 1, max = 200, message = "1 ~ 200 자 내로 입력해주세요.")
    private String musicUrl;

    public RegisterBgmRequest(String musicName, String musicUrl) {
        this.musicName = musicName;
        this.musicUrl = musicUrl;
    }

    public Bgm toEntity() {
        return Bgm.builder()
            .memberId(1L)
            .musicName(musicName)
            .musicUrl(musicUrl)
            .build();
    }
}
