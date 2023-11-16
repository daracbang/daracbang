package a503.daracbang.domain.neighbor.controller;

import a503.daracbang.domain.neighbor.dto.response.MemberSearchResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import a503.daracbang.domain.member.util.MemberContextHolder;
import a503.daracbang.domain.neighbor.dto.response.NeighborResponse;
import a503.daracbang.domain.neighbor.service.NeighborService;
import a503.daracbang.global.response.DataListResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/neighbors")
public class NeighborController {
	private final NeighborService neighborService;


	/*
	 *나의 이웃 목록, 조회한 유저 기준으로 다른 사람의 정보를 리턴한다
	 */
	@GetMapping
	public ResponseEntity<DataListResponse<NeighborResponse>> neighborList() {
		Long myId = MemberContextHolder.memberIdHolder.get();
		return ResponseEntity.ok(neighborService.findNeighborList(myId));
	}

	/*
	 *  내가 신청 받은 회원 정보 리스트.
	 */
	@GetMapping("/accepts")
	public ResponseEntity<DataListResponse<NeighborResponse>> neighborRequestListAccepterMe() {
		Long myId = MemberContextHolder.memberIdHolder.get();
		return ResponseEntity.ok(neighborService.findNeighborAccepterMe(myId));
	}

	@GetMapping("/request")
	public ResponseEntity<DataListResponse<NeighborResponse>> neighborRequestListRequestMe() {
		Long myId = MemberContextHolder.memberIdHolder.get();
		return ResponseEntity.ok(neighborService.findNeighborRequestRequesterMe(myId));
	}


	/*
	 *  닉네임으로 회원 검색.
	 */
	@GetMapping("/search")
	public ResponseEntity<DataListResponse<MemberSearchResponse>> memberList(@RequestParam("nickname") String nickname) {
		Long myId = MemberContextHolder.memberIdHolder.get();
		return ResponseEntity.ok(neighborService.findMemberList(nickname, myId));
	}

	/*
	* 이웃 신청
	*  myId- > 신청자
	* memberId -> 신청 받은 사람.
	*/

	@PostMapping("/applications/{memberId}")
	public ResponseEntity<Void> neighborRequest(@PathVariable("memberId") Long memberId) {
		Long myId = MemberContextHolder.memberIdHolder.get();
		neighborService.requestNeighbor(myId, memberId);

		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/applications/{memberId}")
	public ResponseEntity<Void> neighborRequestCancel(@PathVariable("memberId")Long memberId){
		Long requester = MemberContextHolder.memberIdHolder.get();
		neighborService.requestCancel(requester, memberId);
		return ResponseEntity.ok().build();
	}

	/*
	* 신청 승인
	*/

	@PutMapping("/accepts/{neighborId}")
	public ResponseEntity<Void> neighborAccept(@PathVariable("neighborId") Long neighborId) {
		Long myId = MemberContextHolder.memberIdHolder.get();
		neighborService.acceptNeighbor(myId, neighborId);

		return ResponseEntity.ok().build();
	}

	// 거절, 삭제 통합
	@DeleteMapping("/{neighborId}")
	public ResponseEntity<Void> neighborRemove(@PathVariable("neighborId") Long neighborId) {
		Long myId = MemberContextHolder.memberIdHolder.get();
		neighborService.removeNeighbor(neighborId, myId);
		return ResponseEntity.noContent().build();
	}

}
