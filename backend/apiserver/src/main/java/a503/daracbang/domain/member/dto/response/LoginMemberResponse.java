package a503.daracbang.domain.member.dto.response;

import lombok.Getter;

@Getter
public class LoginMemberResponse {

	private String jwt;

	public LoginMemberResponse(String jwt) {
		this.jwt = jwt;
	}
}
