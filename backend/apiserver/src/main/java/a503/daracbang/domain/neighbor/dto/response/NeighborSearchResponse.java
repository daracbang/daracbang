package a503.daracbang.domain.neighbor.dto.response;


import a503.daracbang.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NeighborSearchResponse {

    private final Long memberId;
    private final String nickname;
    private final String profileImage;
    private final Boolean isCon;

    @Builder
    public NeighborSearchResponse(Long memberId, String nickname, String profileImage,
            Boolean isCon) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.isCon = isCon;
    }

    public static NeighborSearchResponse from(Member member, Boolean isCon) {
        return NeighborSearchResponse.builder()
                                     .isCon(isCon)
                                     .memberId(member.getId())
                                     .nickname(member.getNickname())
                                     .profileImage(member.getProfileImage())
                                     .build();
    }

}
