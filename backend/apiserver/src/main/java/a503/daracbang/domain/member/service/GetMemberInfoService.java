package a503.daracbang.domain.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import a503.daracbang.domain.member.dto.response.MemberInfoResponse;
import a503.daracbang.domain.member.entity.Member;
import a503.daracbang.domain.member.exception.MemberErrorCode;
import a503.daracbang.domain.member.repository.MemberRepository;
import a503.daracbang.global.exception.CustomException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class GetMemberInfoService {

	private final MemberRepository memberRepository;

	public MemberInfoResponse getMemberInfo(Long id) {
		Member member = memberRepository.findById(id)
			.orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
		MemberInfoResponse memberInfoResponse
			= MemberInfoResponse.builder()
								.loginId(member.getLoginId())
								.nickname(member.getNickname())
								.profileImage(member.getProfileImage())
								.introduce(member.getIntroduce())
								.build();

		return memberInfoResponse;
	}

	public Long getMemberId(String nickname) {
		Member member = memberRepository.findByNickname(nickname)
			.orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
		return member.getId();
	}

	public Member getMember(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));
	}
}
