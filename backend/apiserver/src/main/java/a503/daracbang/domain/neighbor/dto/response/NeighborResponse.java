package a503.daracbang.domain.neighbor.dto.response;

import a503.daracbang.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class NeighborResponse {
	private final Long neighborId;
	private final Long memberId;
	private final String nickname;
	private final String profileImage;

	public static NeighborResponse from(Long neighborId, Member member) {
		return NeighborResponse.of(neighborId, member.getId(), member.getNickname(),
			member.getProfileImage());
	}
}
