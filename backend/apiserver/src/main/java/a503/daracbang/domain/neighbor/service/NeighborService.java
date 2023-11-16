package a503.daracbang.domain.neighbor.service;

import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.domain.member.repository.MemberRepository;
import a503.daracbang.domain.neighbor.dto.response.MemberSearchResponse;
import a503.daracbang.domain.neighbor.dto.response.NeighborResponse;
import a503.daracbang.domain.neighbor.entity.Neighbor;
import a503.daracbang.domain.neighbor.exception.NeighborErrorCode;
import a503.daracbang.domain.neighbor.repository.NeighborRepository;
import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.response.DataListResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NeighborService {

    private final NeighborRepository neighborRepository;
    private final MemberRepository memberRepository;

    /*
     * 나의 이웃 목록, 조회한 유저 기준으로 다른 사람의 정보를 리턴한다
     */
    @Transactional(readOnly = true)
    public DataListResponse<NeighborResponse> findNeighborList(Long memberId) {
        return new DataListResponse<>(neighborRepository.findAllMyNeighbor(memberId)
                                                        .stream()
                                                        .map(
                                                                (neighbor -> {
                                                                    if (neighbor.getAccepter()
                                                                                .getId()
                                                                                .equals(memberId)) {
                                                                        return NeighborResponse.from(
                                                                                neighbor.getId(),
                                                                                neighbor.getRequester());
                                                                    }
                                                                    return NeighborResponse.from(
                                                                            neighbor.getId(),
                                                                            neighbor.getAccepter());
                                                                }))
                                                        .toList()
        );
    }

    /*
     *  내가 신청 받은 회원 정보 리스트.
     */
    @Transactional(readOnly = true)
    public DataListResponse<NeighborResponse> findNeighborAccepterMe(Long myId) {
        Member me = findById(myId);
        return new DataListResponse<>(
                neighborRepository.findAllByIsConFalseAndAccepter(me)
                                  .stream()
                                  .map(n -> NeighborResponse.from(n.getId(), n.getRequester()))
                                  .toList()
        );
    }

    /*
     * 내가 신청한 회원 리스트 ( 아직 이웃 아닌)
     */
    @Transactional(readOnly = true)
    public DataListResponse<NeighborResponse> findNeighborRequestRequesterMe(Long myId) {
        Member me = findById(myId);
        return new DataListResponse<>(
                neighborRepository.findAllByIsConFalseAndRequester(me)
                                  .stream()
                                  .map(n -> NeighborResponse.from(n.getId(), n.getAccepter()))
                                  .toList()
        );
    }

    /*
     * 닉네임으로 회원을 검색한다.
     */
    @Transactional(readOnly = true)
    public DataListResponse<MemberSearchResponse> findMemberList(String nickname, Long requesterId) {
        List<MemberSearchResponse> result = memberRepository.searchMembers(nickname,
                requesterId);
        return new DataListResponse<>(result);
    }

    /*
     *  이웃 신청
     *  my -> memberId로 신청한다.
     */
    @Transactional
    public void requestNeighbor(Long myId, Long memberId) {
        Optional<Neighbor> isNeighbor = neighborRepository.findByMemberIdPair(myId, memberId);
        if (isNeighbor.isPresent()) {
            throw new CustomException(NeighborErrorCode.ALREADY_NEIGHBOR);
        }

        Member me = findById(myId);
        Member you = findById(memberId);

        // 수락이 안된 상태로 DB에 insert
        neighborRepository.save(Neighbor.builder()
                                        .requester(me)
                                        .accepter(you)
                                        .isRequest(false)
                                        .build());
    }


    @Transactional
    public void requestCancel(Long requestId, Long memberId){
        Member requester = findById(requestId);
        Member acctepter = findById(memberId);
        Neighbor neighbor = neighborRepository.findByRequesterAndAccepter(requester, acctepter)
                                              .orElseThrow(() -> new CustomException(
                                                      NeighborErrorCode.NEIGHBOR_NOT_FOUND));
        if(neighbor.isCon()){
            // 이미 승인된 경우
            throw new CustomException(NeighborErrorCode.ALREADY_NEIGHBOR);
        }

        neighborRepository.delete(neighbor);
    }

    @Transactional
    public void removeNeighbor(Long neighborId, Long memberId) {
        Neighbor neighbor = findNeighborById(neighborId);
        neighborRepository.delete(neighbor);
    }

    /*
     * 1. 승인하기
     */
    @Transactional
    public void acceptNeighbor(Long myId, Long neighborId) {
        Member accepter = findById(myId);
        Neighbor neighbor = findNeighborById(neighborId);

        if(neighbor.isCon()){
            throw new CustomException(NeighborErrorCode.ALREADY_NEIGHBOR);
        }
        neighbor.acceptNeighbor(accepter);
    }


    @Transactional
    public boolean isNeighbor(Long accepterId, Long requesterId) {
        Optional<Boolean> myNeighborStatus = neighborRepository.findIsConByAccepterIdAndRequesterId(
                accepterId, requesterId);
        Optional<Boolean> requesterNeighborStatus = neighborRepository.findIsConByAccepterIdAndRequesterId(
                requesterId, accepterId);
		if (myNeighborStatus.isEmpty() || requesterNeighborStatus.isEmpty()) {
			return false;
		}
        return myNeighborStatus.get() && requesterNeighborStatus.get();
    }

    private Member findById(Long id) {
        return memberRepository.findById(id)
                               .orElseThrow(
                                       () -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
    }

    private Neighbor findNeighborById(Long id) {
        return neighborRepository.findById(id)
                                 .orElseThrow(() -> new CustomException(
                                         NeighborErrorCode.NEIGHBOR_NOT_FOUND));
    }
}
