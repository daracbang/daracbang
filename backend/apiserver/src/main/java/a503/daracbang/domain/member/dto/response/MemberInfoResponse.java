package a503.daracbang.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfoResponse {

	private Long id;

	private String loginId;

	private String nickname;

	private String profileImage;

	private String introduce;

	@Builder
	public MemberInfoResponse(Long id, String loginId, String nickname, String profileImage, String introduce) {
		this.id = id;
		this.loginId = loginId;
		this.nickname = nickname;
		this.profileImage = profileImage;
		this.introduce = introduce;
	}
}
