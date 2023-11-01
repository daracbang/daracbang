package a503.daracbang.domain.member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntroduceRequest {

	private String introduce;

	public IntroduceRequest(String introduce) {
		this.introduce = introduce;
	}
}
