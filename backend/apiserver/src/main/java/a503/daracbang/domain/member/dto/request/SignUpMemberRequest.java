package a503.daracbang.domain.member.dto.request;

import a503.daracbang.domain.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpMemberRequest {

	@NotBlank
	@Size(min = 2, max = 10, message = "로그인 ID는 2자 이상, 10자 이하로 입력해주세요.")
	@Pattern(regexp = "^[a-zA-Z0-9]*$")
	private String loginId;

	@NotBlank
	@Size(min = 4, max = 20, message = "비밀번호는 4자 이상, 20자 이하로 입력해주세요.")
	private String password;

	@NotBlank
	@Size(min = 2, max = 10)
	@Pattern(regexp = "^[a-zA-Z0-9가-힣]*$")
	private String nickname;

	private String profileImage;

	@Builder
	public SignUpMemberRequest(String loginId, String password, String nickname, String profileImage) {
		this.loginId = loginId;
		this.password = password;
		this.nickname = nickname;
		this.profileImage = profileImage;
	}

	public Member toEntity() {
		return Member.builder()
			.loginId(loginId)
			.password(password)
			.nickname(nickname)
			.profileImage(profileImage)
			.build();
	}
}
