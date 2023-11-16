package a503.daracbang.domain.neighbor.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSearchResponse {

    private final Long memberId;
    private final String nickname;
    private final String profileImage;
    private final Boolean isNeighbor;
    private final Boolean isNeighborRequest;


    @Builder
    public MemberSearchResponse(Long memberId, String nickname, String profileImage,
            Boolean isNeighbor,
            Boolean isNeighborRequest) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.isNeighbor = isNeighbor;
        this.isNeighborRequest = isNeighborRequest;
    }

}
