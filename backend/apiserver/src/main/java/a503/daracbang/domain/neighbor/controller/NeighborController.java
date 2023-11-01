package a503.daracbang.domain.neighbor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import a503.daracbang.domain.neighbor.dto.response.NeighborResponse;
import a503.daracbang.domain.neighbor.service.NeighborService;
import a503.daracbang.global.response.DataListResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/neighbors")
public class NeighborController {
	private final NeighborService neighborService;

	@GetMapping
	public ResponseEntity<DataListResponse<NeighborResponse>> neighborList() {
		// Long myId = MemberContextHolder.memberIdHolder.get();
		Long myId = 1L;
		return ResponseEntity.ok(neighborService.findNeighborList(myId));
	}

	@GetMapping("/accepts")
	public ResponseEntity<DataListResponse<NeighborResponse>> neighborRequestList() {
		// Long myId = MemberContextHolder.memberIdHolder.get();
		Long myId = 2L;
		return ResponseEntity.ok(neighborService.findNeighborRequestList(myId));
	}
	@PostMapping("/applications/{memberId}")
	public ResponseEntity<Void> neighborRequest(@PathVariable("memberId") Long memberId) {
		// Long myId = MemberContextHolder.memberIdHolder.get();
		Long myId = 1L;
		neighborService.requestNeighbor(myId, memberId);

		return ResponseEntity.ok().build();
	}

	@PutMapping("/accepts/{memberId}")
	public ResponseEntity<Void> neighborAccept(@PathVariable("memberId") Long memberId) {
		// Long myId = MemberContextHolder.memberIdHolder.get();
		Long myId = 2L;
		neighborService.acceptNeighbor(myId, memberId);

		return ResponseEntity.ok().build();
	}

	// 취소, 거절, 삭제 통합
	@DeleteMapping("/api/neighbors/{memberId}")
	public ResponseEntity<Void> neighborRemove(@PathVariable("memberId") Long memberId) {
		// Long myId = MemberContextHolder.memberIdHolder.get();
		Long myId = 2L;
		neighborService.removeNeighbor(myId, memberId);
		return ResponseEntity.noContent().build();
	}
}
