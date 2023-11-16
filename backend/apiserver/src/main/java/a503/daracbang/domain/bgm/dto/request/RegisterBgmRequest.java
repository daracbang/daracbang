package a503.daracbang.domain.bgm.dto.request;

import a503.daracbang.domain.bgm.entity.Bgm;
import a503.daracbang.domain.member.entity.Member;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 유튜브 동영상 ID 값이 아닌 링크로 대체할 때 사용
 */
@Getter
@NoArgsConstructor
public class RegisterBgmRequest {

    @Size(min = 1, max = 100, message = "1 ~ 100 자 내로 입력해주세요.")
    private String bgmName;

    @Size(min = 1, max = 200, message = "1 ~ 200 자 내로 입력해주세요.")
    private String url;

    public RegisterBgmRequest(String bgmName, String url) {
        this.bgmName = bgmName;
        this.url = url;
    }

    public Bgm toEntity(Member member) {
        return new Bgm(member, bgmName, url);
    }
}
