package a503.daracbang.domain.bgm.dto.request;

import a503.daracbang.domain.bgm.entity.Bgm;
import a503.daracbang.domain.member.entity.Member;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RegisterBgmIdRequest {

    @Size(min = 1, max = 100, message = "1 ~ 100 자 내로 입력해주세요.")
    private String videoId;

    @Size(min = 1, max = 200, message = "1 ~ 200 자 내로 입력해주세요.")
    private String bgmName;

    public RegisterBgmIdRequest(String videoId, String bgmName) {
        this.videoId = videoId;
        this.bgmName = bgmName;
    }

    public Bgm toEntity(Member member) {
        return new Bgm(member, bgmName, videoId, "default url");
    }
}
