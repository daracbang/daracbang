package a503.daracbang.domain.neighbor.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class NeighborResponse {
	private final Long memberId;
	private final String nickname;
	private final String profileImage;
}
