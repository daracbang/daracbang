package a503.daracbang.domain.neighbor.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NeighborResponse {
	private Long memberId;
	private String nickname;
	private String profileImage;

	public static NeighborResponse of(Long memberId, String nickname, String profileImage) {
		return new NeighborResponse(memberId, nickname, profileImage);
	}
}
