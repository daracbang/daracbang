package a503.daracbang.domain.member.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter
public class LoginMemberRequest {

	private String loginId;

	private String password;

	@Builder
	public LoginMemberRequest(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}
	public LoginMemberRequest(){
	}
}
