package a503.daracbang.domain.member.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IntroduceRequest {

	private String introduce;

	public IntroduceRequest(String introduce) {
		this.introduce = introduce;
	}
}
