package a503.daracbang.domain.neighbor.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NeighborListResponse {
	private Long memberId;
	private String nickname;
	private String profileImage;

	public static NeighborListResponse of(Long memberId, String nickname, String profileImage) {
		return new NeighborListResponse(memberId, nickname, profileImage);
	}
}
