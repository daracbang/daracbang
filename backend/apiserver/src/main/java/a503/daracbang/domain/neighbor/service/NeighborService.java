package a503.daracbang.domain.neighbor.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.domain.member.repository.MemberRepository;
import a503.daracbang.domain.neighbor.dto.response.NeighborResponse;
import a503.daracbang.domain.neighbor.entity.Neighbor;
import a503.daracbang.domain.neighbor.exception.NeighborErrorCode;
import a503.daracbang.domain.neighbor.repository.NeighborRepository;
import a503.daracbang.global.exception.CustomException;
import a503.daracbang.global.response.DataListResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NeighborService {
	private final NeighborRepository neighborRepository;
	private final MemberRepository memberRepository;

	@Transactional(readOnly = true)
	public DataListResponse<NeighborResponse> findNeighborList(Long memberId) {
		return new DataListResponse<>(
			neighborRepository.findAllMyNeighbor(memberId).stream()
				.map(n -> NeighborResponse.from(n.getAccepter()))
				.toList()
		);
	}

	@Transactional(readOnly = true)
	public DataListResponse<NeighborResponse> findNeighborRequestList(Long myId) {
		Member me = findById(myId);
		return new DataListResponse<>(
			neighborRepository.findAllByIsConFalseAndRequester(me).stream()
				.map(n -> NeighborResponse.from(n.getAccepter()))
				.toList()
		);
	}

	@Transactional
	public void requestNeighbor(Long myId, Long memberId) {
		Member me = findById(myId);
		Member you = findById(memberId);

		// 이미 신청했거나, 받았거나, 추가된 이웃인지 확인
		if (neighborRepository.existsByRequesterAndAccepter(me, you)) {
			throw new CustomException(NeighborErrorCode.ALREADY_NEIGHBOR);
		}

		// 이웃 신청시 양방향으로 2개 INSERT
		neighborRepository.save(Neighbor.builder().requester(me).accepter(you).isRequest(true).build());
		neighborRepository.save(Neighbor.builder().requester(you).accepter(me).isRequest(false).build());
	}

	@Transactional
	public void removeNeighbor(Long myId, Long memberId) {
		Member me = findById(myId);
		Member you = findById(memberId);

		neighborRepository.deleteByRequesterAndAccepter(me, you);
		neighborRepository.deleteByRequesterAndAccepter(you, me);
	}

	@Transactional
	public void acceptNeighbor(Long myId, Long memberId) {
		Member me = findById(myId);
		Member you = findById(memberId);

		// 내쪽에서 시작하는 방향을 true로 바꿔줌 = 신청 받아들임
		Neighbor neighbor = neighborRepository.findByRequesterAndAccepter(me, you);
		LocalDateTime acceptedAt = neighbor.acceptNeighbor();

		// 수락시간 반대 방향에서도 변경
		neighbor = neighborRepository.findByRequesterAndAccepter(you, me);
		neighbor.updateAcceptedAt(acceptedAt);

	}

	private Member findById(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new CustomException(MemberErrorCode.NOTFOUND_MEMBER));
	}

}
